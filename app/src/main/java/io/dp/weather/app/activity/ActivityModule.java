package io.dp.weather.app.activity;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.AppModule;

/**
 * Created by dp on 07/10/14.
 */

@Module(
    injects = {
        MainActivity.class,
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
}
