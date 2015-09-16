package io.dp.weather.app.activity;

import android.os.Bundle;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import io.dp.weather.app.MockAppComponent;
import io.dp.weather.app.WeatherApp;
import io.dp.weather.app.activity.debug.DebugBusModule;

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
    ActivityComponent component = DaggerActivityComponent.builder()
        .appComponent(app.getComponent())
        .activityModule(new ActivityModule(this))
        .build();

    return component.plus(new DebugBusModule());
  }

  @Override public BaseActivityComponent getComponent() {
    return component;
  }
}
