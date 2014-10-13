package io.dp.weather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

/**
 * Shadow for {@code SQLiteOpenHelper}.  Provides basic support for retrieving
 * databases and partially implements the subclass contract.  (Currently,
 * support for {@code #onUpgrade} is missing).
 */
@Implements(SQLiteOpenHelper.class)
public class FixedShadowSQLiteOpenHelper {

  @RealObject
  private SQLiteOpenHelper realHelper;
  private static SQLiteDatabase database;

  public void __constructor__(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//    if (database != null) {
//      database.
//    }
//    database = null;
  }

  @Implementation
  public synchronized void close() {
    if (database != null) {
      database.close();
    }
    database = null;
  }

  @Implementation
  public synchronized SQLiteDatabase getReadableDatabase() {
    if (database == null) {
      database = SQLiteDatabase.create(null);//SQLiteDatabase.openDatabase("path", null, 0);
      realHelper.onCreate(database);
    }

    realHelper.onOpen(database);
    return database;
  }

  @Implementation
  public synchronized SQLiteDatabase getWritableDatabase() {
    if (database == null) {
      database = SQLiteDatabase.create(null);//SQLiteDatabase.openDatabase("path", null, 0);
      realHelper.onCreate(database);
    }

    realHelper.onOpen(database);
    return database;
  }
}
