package io.dp.weather.app.activity;

import dagger.Component;
import io.dp.weather.app.AppComponent;
import io.dp.weather.app.BusModule;
import io.dp.weather.app.annotation.PerActivity;
import io.dp.weather.app.fragment.WeatherFragment;

/**
 * Created by deepol on 19/08/15.
 */
@PerActivity @Component(
    modules = { ActivityModule.class, BusModule.class },
    dependencies = AppComponent.class)
public interface ActivityComponent extends BaseActivityComponent {

  void inject(WeatherFragment weatherFragment);

  void inject(BaseActivity baseActivity);
}
