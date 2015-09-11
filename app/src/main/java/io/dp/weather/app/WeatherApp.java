package io.dp.weather.app;

import android.app.Application;

/**
 * Created by dp on 07/10/14.
 */
public class WeatherApp extends Application {

  private AppComponent component;

  @Override public void onCreate() {
    super.onCreate();

    this.component = createComponent();
  }

  public AppComponent getComponent() {
    return component;
  }

  public AppComponent createComponent() {
    return DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
  }
}
