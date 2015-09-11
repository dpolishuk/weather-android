package io.dp.weather.app.activity.debug;

import android.os.Bundle;
import com.squareup.otto.Bus;
import io.dp.weather.app.R;
import io.dp.weather.app.WeatherApp;
import io.dp.weather.app.activity.ActivityModule;
import io.dp.weather.app.activity.BaseActivity;
import io.dp.weather.app.activity.BaseActivityComponent;
import javax.inject.Inject;

public class DebugActivity extends BaseActivity {
  @Inject
  Bus bus;

  @Override
  public BaseActivityComponent createComponent() {
    WeatherApp app = (WeatherApp) getApplication();
    return DaggerDebugActivityComponent.builder()
        .appComponent(app.getComponent())
        .activityModule(new ActivityModule(this))
        .build();
  }

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.activity_debug);
    ((DebugActivityComponent) getComponent()).inject(this);
  }
}
