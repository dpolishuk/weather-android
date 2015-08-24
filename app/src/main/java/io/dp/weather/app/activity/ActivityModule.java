package io.dp.weather.app.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.annotation.CachePrefs;
import io.dp.weather.app.annotation.ConfigPrefs;
import io.dp.weather.app.annotation.PerActivity;
import io.dp.weather.app.db.DatabaseHelper;

/**
 * Created by dp on 07/10/14.
 */

@Module
public class ActivityModule {

  private final RxFragmentActivity activity;

  public ActivityModule(RxFragmentActivity activity) {
    this.activity = activity;
  }

  @Provides
  @PerActivity
  public RxFragmentActivity provideActivity() {
    return activity;
  }

  @Provides
  @PerActivity
  public DatabaseHelper provideDatabaseHelper() {
    return OpenHelperManager.getHelper(activity, DatabaseHelper.class);
  }

  @Provides
  @ConfigPrefs
  @PerActivity
  public SharedPreferences provideConfigPrefs() {
    return PreferenceManager.getDefaultSharedPreferences(activity);
  }

  @Provides
  @CachePrefs
  @PerActivity
  public SharedPreferences provideCachePrefs() {
    return activity.getSharedPreferences("cachePrefs", Context.MODE_PRIVATE);
  }

  public void releaseDatabaseHelper() {
    OpenHelperManager.releaseHelper();
  }
}
