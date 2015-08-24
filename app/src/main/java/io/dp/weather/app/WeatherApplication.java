package io.dp.weather.app;

import android.app.Application;

/**
 * Created by dp on 07/10/14.
 */
public class WeatherApplication extends Application {

  private AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();

    this.component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    component.inject(this);
  }

  public AppComponent getComponent() {
    return component;
  }
}
