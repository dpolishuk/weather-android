package io.dp.weather.app;

import dagger.Subcomponent;
import io.dp.weather.app.activity.BaseActivityComponent;
import io.dp.weather.app.annotation.PerActivity;
import io.dp.weather.app.fragment.WeatherFragment;

/**
 * Created by deepol on 16/09/15.
 */
@PerActivity
@Subcomponent(modules = BusModule.class)
public interface BusSubcomponent extends BaseActivityComponent {

  void inject(WeatherFragment fragment);
}
