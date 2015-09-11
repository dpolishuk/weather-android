package io.dp.weather.app.activity;

import android.os.Bundle;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import io.dp.weather.app.WeatherApp;

/**
 * Created by deepol on 11/09/15.
 */
public class MockActivity extends RxFragmentActivity implements HasComponent<BaseActivityComponent> {
  BaseActivityComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.component = createComponent();
  }

  @Override public BaseActivityComponent createComponent() {
    WeatherApp app = (WeatherApp) getApplication();
    return DaggerActivityComponent.builder()
        .appComponent(app.getComponent())
        .activityModule(new ActivityModule(this))
        .build();
  }

  @Override public BaseActivityComponent getComponent() {
    return component;
  }
}
