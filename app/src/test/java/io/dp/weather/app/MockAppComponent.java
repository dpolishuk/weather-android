package io.dp.weather.app;

import dagger.Component;
import io.dp.weather.app.fragment.WeatherFragmentTest;
import javax.inject.Singleton;

/**
 * Created by deepol on 11/09/15.
 */
@Singleton
@Component(modules = { MockAppModule.class })
public interface MockAppComponent extends AppComponent {
  void inject(WeatherFragmentTest weatherFragmentTest);
}
