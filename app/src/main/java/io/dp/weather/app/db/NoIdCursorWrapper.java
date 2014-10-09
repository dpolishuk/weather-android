package io.dp.weather.app.db;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.provider.BaseColumns;

/**
 * Created by dp on 09/10/14.
 */
public class NoIdCursorWrapper extends CursorWrapper {

  private int idColumnIndex;

  /**
   * Create a NoIdCursorWrapper using the alias column index.
   *
   * @param c             the cursor to wrap
   * @param idColumnIndex the column index to use as the _id column alias
   */
  public NoIdCursorWrapper(Cursor c, int idColumnIndex) {
    super(c);
    this.idColumnIndex = idColumnIndex;
  }

  /**
   * Create a NoIdCursorWrapper using the alias column name.
   *
   * @param c            the cursor to wrap
   * @param idColumnName the column name to use as the _id column alias
   */
  public NoIdCursorWrapper(Cursor c, String idColumnName) {
    super(c);
    idColumnIndex = c.getColumnIndex(idColumnName);
  }

  @Override
  public int getColumnIndex(String columnName) {
    int index = super.getColumnIndex(columnName);
    if (index < 0 && BaseColumns._ID.equals(columnName)) {
      index = idColumnIndex;
    }
    return index;
  }

  @Override
  public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
    int index = getColumnIndex(columnName);
    if (index >= 0) {
      return index;
    }
    // let the AbstractCursor generate the exception
    return super.getColumnIndexOrThrow(columnName);
  }
}
