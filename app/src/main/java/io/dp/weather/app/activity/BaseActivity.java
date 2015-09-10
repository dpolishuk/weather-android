package io.dp.weather.app.activity;

import android.os.Bundle;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import io.dp.weather.app.WeatherApplication;

/**
 * Created by dp on 07/10/14.
 */
public abstract class BaseActivity extends RxAppCompatActivity {

  private BaseActivityComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.component = createComponent();
  }

  public BaseActivityComponent getComponent() {
    return component;
  }

  protected BaseActivityComponent createComponent() {
    WeatherApplication app = (WeatherApplication) getApplication();
    return DaggerActivityComponent.builder()
        .appComponent(app.getComponent())
        .activityModule(new ActivityModule(this))
        .build();
  }
}
