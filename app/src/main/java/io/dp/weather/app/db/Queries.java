package io.dp.weather.app.db;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

import io.dp.weather.app.db.table.Place;

/**
 * Created by dp on 09/10/14.
 */
public class Queries {

  public static PreparedQuery<Place> prepareCityQuery(DatabaseHelper dbHelper) {
    try {
      QueryBuilder<Place, Long> qb = dbHelper.getPlaceDao().queryBuilder();
      return qb.prepare();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }
}
