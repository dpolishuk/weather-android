package io.dp.weather.app.activity.debug;

import android.os.Bundle;
import com.squareup.otto.Bus;
import io.dp.weather.app.R;
import io.dp.weather.app.WeatherApplication;
import io.dp.weather.app.activity.ActivityModule;
import io.dp.weather.app.activity.BaseActivity;
import io.dp.weather.app.activity.BaseActivityComponent;
import javax.inject.Inject;
import timber.log.Timber;

public class DebugActivity extends BaseActivity {

  @Inject Bus bus;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_debug);

    ((DebugActivityComponent) getComponent()).inject(this);

    Timber.v("!");
  }

  @Override protected BaseActivityComponent createComponent() {
    WeatherApplication app = (WeatherApplication) getApplication();
    return DaggerDebugActivityComponent.builder()
        .appComponent(app.getComponent())
        .activityModule(new ActivityModule(this))
        .build();
  }
}
