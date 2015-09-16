package io.dp.weather.app.activity;

import android.os.Bundle;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import io.dp.weather.app.BusModule;
import io.dp.weather.app.WeatherApp;

/**
 * Created by dp on 07/10/14.
 */
public class BaseActivity extends RxAppCompatActivity
    implements HasComponent<BaseActivityComponent> {
  private BaseActivityComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.component = createComponent();
  }

  public BaseActivityComponent getComponent() {
    return component;
  }

  @Override public BaseActivityComponent createComponent() {
      WeatherApp app = (WeatherApp) getApplication();
      ActivityComponent component = DaggerActivityComponent.builder()
          .appComponent(app.getComponent())
          .activityModule(new ActivityModule(this))
          .build();

      return component.plus(new BusModule());
  }
}
