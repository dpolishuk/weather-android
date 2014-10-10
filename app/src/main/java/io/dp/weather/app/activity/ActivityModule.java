package io.dp.weather.app.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.AppModule;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.fragment.WeatherFragment;

/**
 * Created by dp on 07/10/14.
 */

@Module(
    injects = {
        MainActivity.class,
        WeatherFragment.class
    },
    addsTo = AppModule.class,
    library = true)
public class ActivityModule {

  private Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides
  public Activity provideActivity() {
    return activity;
  }

  @Provides
  public DatabaseHelper provideDatabaseHelper() {
    return OpenHelperManager.getHelper(activity, DatabaseHelper.class);
  }

  @Provides
  public SharedPreferences provideSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(activity);
  }

  public void releaseDatabaseHelper() {
    OpenHelperManager.releaseHelper();
  }
}
