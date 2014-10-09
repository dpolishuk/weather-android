package io.dp.weather.app.db;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by dp on 09/10/14.
 */
public class OrmliteCursorLoader<T> extends AsyncTaskLoader<Cursor> {

  final ForceLoadContentObserver observer;

  private Cursor cursor;
  private Dao<T, ?> dao;
  private PreparedQuery<T> query;

  public OrmliteCursorLoader(Context context, Dao<T, ?> dao,
                             PreparedQuery<T> query) {
    super(context);
    observer = new ForceLoadContentObserver();
    this.dao = dao;
    this.query = query;
  }

  /* Runs on a worker thread */
  @Override
  public Cursor loadInBackground() {
    Cursor cursor = null;
    try {
      cursor = getCursor(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (cursor != null) {
      // Ensure the cursor window is filled
      cursor.getCount();
      registerContentObserver(cursor, observer);
    }
    return cursor;
  }

  public Cursor getCursor(PreparedQuery<T> query) throws SQLException {
    BaseDaoImpl<T, ?> baseDao = (BaseDaoImpl<T, ?>) dao;
    CloseableIterator<T> iterator = dao.iterator(query);
    AndroidDatabaseResults results = (AndroidDatabaseResults) iterator.getRawResults();
    String idColumnName = baseDao.getTableInfo().getIdField().getColumnName();
    return new NoIdCursorWrapper(results.getRawCursor(), idColumnName);
  }

  /**
   * Registers an observer to get notifications from the content provider when the cursor needs to
   * be refreshed.
   */
  void registerContentObserver(Cursor cursor, ContentObserver observer) {
    cursor.registerContentObserver(this.observer);
  }

  /* Runs on the UI thread */
  @Override
  public void deliverResult(Cursor cursor) {
    if (isReset()) {
      // An async query came in while the loader is stopped
      if (cursor != null) {
        cursor.close();
      }
      return;
    }
    Cursor oldCursor = this.cursor;
    this.cursor = cursor;

    if (isStarted()) {
      super.deliverResult(cursor);
    }

    if (oldCursor != null && oldCursor != cursor && !oldCursor.isClosed()) {
      oldCursor.close();
    }
  }

  @Override
  protected void onStartLoading() {
    if (cursor != null) {
      deliverResult(cursor);
    }
    if (takeContentChanged() || cursor == null) {
      forceLoad();
    }
  }

  /**
   * Must be called from the UI thread
   */
  @Override
  protected void onStopLoading() {
    // Attempt to cancel the current load task if possible.
    cancelLoad();
  }

  @Override
  public void onCanceled(Cursor cursor) {
    if (cursor != null && !cursor.isClosed()) {
      cursor.close();
    }
  }

  @Override
  protected void onReset() {
    super.onReset();

    // Ensure the loader is stopped
    onStopLoading();

    if (cursor != null && !cursor.isClosed()) {
      cursor.close();
    }
    cursor = null;
  }


  public PreparedQuery<T> getQuery() {
    return query;
  }

  public void setQuery(PreparedQuery<T> mQuery) {
    this.query = mQuery;
  }

  public Dao<T, ?> getDao() {
    return dao;
  }

  @Override
  public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
    super.dump(prefix, fd, writer, args);
    writer.print(prefix);
    writer.print("cursor=");
    writer.println(cursor);
  }
}
