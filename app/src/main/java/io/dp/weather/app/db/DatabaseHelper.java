package io.dp.weather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import io.dp.weather.app.BuildConfig;
import io.dp.weather.app.R;
import io.dp.weather.app.db.table.City;
import timber.log.Timber;

/**
 * Created by dp on 09/10/14.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "weather.db";
  private static final int DATABASE_VERSION = BuildConfig.DATABASE_VERSION;

  Context context;

  private volatile Dao<City, Long> cityDao = null;

  City[] predefinedCities = {
      new City("Dublin", 53.34410, -6.2674),
      new City("London", 51.51121, -0.1198),
      new City("New York", 40.71278, -74.00594),
      new City("Barcelona", 41.3850, 2.1734)
  };

  public DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    Timber.v("! onCreateDatabase");

    try {
      TableUtils.createTableIfNotExists(connectionSource, City.class);
      Dao<City, Long> cityDao = getCityDao();
      for (City city : predefinedCities) {
        cityDao.createIfNotExists(city);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
                        int newVersion) {
    Timber.v("! onUpgradeDatabase");
  }

  public Dao<City, Long> getCityDao() throws SQLException {
    Dao<City, Long> resultDao = cityDao;

    if (resultDao == null) {
      synchronized (this) {
        resultDao = cityDao;

        if (resultDao == null) {
          cityDao = resultDao = getDao(City.class);
        }
      }
    }
    return resultDao;
  }

  @Override
  public void close() {
    super.close();

    cityDao = null;
  }
}
