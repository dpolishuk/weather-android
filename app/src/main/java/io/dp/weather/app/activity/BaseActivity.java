package io.dp.weather.app.activity;

import android.os.Bundle;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import io.dp.weather.app.WeatherApplication;

/**
 * Created by dp on 07/10/14.
 */
public abstract class BaseActivity extends RxFragmentActivity {

  private ActivitySubcomponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    WeatherApplication app = (WeatherApplication) getApplication();
    this.component = app.getComponent().plus(new ActivityModule(this));
  }

  public ActivitySubcomponent getComponent() {
    return component;
  }
}
