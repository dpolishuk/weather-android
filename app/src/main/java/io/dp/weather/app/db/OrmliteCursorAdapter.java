package io.dp.weather.app.db;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;

/**
 * Created by dp on 09/10/14.
 */
public abstract class OrmliteCursorAdapter<T> extends CursorAdapter {

  PreparedQuery<T> query;

  public OrmliteCursorAdapter(Context context, Cursor c, PreparedQuery<T> query) {
    super(context, c, false);
    this.query = query;
  }

  @Override
  public void bindView(View itemView, Context context, Cursor cursor) {
    try {
      T item = query.mapRow(new AndroidDatabaseResults(cursor, null));
      bindView(itemView, context, item);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public T getItem(int position) {
    Cursor c = (Cursor) super.getItem(position);
    try {
      return query.mapRow(new AndroidDatabaseResults(c, null));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public void setQuery(PreparedQuery<T> query) {
    this.query = query;
  }

  public PreparedQuery<T> getQuery() {
    return query;
  }

  abstract public void bindView(View itemView, Context context, T item);
}
