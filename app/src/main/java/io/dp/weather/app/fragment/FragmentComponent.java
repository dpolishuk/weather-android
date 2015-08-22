package io.dp.weather.app.fragment;

import android.app.Activity;
import dagger.Component;
import io.dp.weather.app.activity.ActivityComponent;
import io.dp.weather.app.annotation.PerFragment;
import io.dp.weather.app.net.PlacesApi;
import io.dp.weather.app.net.WeatherApi;

/**
 * Created by deepol on 19/08/15.
 */
@PerFragment @Component(dependencies = ActivityComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
  void inject(WeatherFragment fragment);

  Activity provideActivity();

  WeatherApi provideForecastApi();

  PlacesApi providePlacesApi();
}
