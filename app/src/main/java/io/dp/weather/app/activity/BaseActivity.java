package io.dp.weather.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import io.dp.weather.app.WeatherApplication;

/**
 * Created by dp on 07/10/14.
 */
public abstract class BaseActivity extends AppCompatActivity {

  private ActivityComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    WeatherApplication app = (WeatherApplication) getApplication();
    this.component = DaggerActivityComponent.builder()
        .appComponent(app.getComponent())
        .activityModule(new ActivityModule(this))
        .build();
  }

  public ActivityComponent getComponent() {
    return component;
  }
}
