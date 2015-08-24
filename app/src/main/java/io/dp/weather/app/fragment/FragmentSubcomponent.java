package io.dp.weather.app.fragment;

import dagger.Subcomponent;
import io.dp.weather.app.annotation.PerFragment;

/**
 * Created by deepol on 19/08/15.
 */
@PerFragment @Subcomponent(modules = FragmentModule.class)
public interface FragmentSubcomponent {
  void inject(WeatherFragment fragment);
}
