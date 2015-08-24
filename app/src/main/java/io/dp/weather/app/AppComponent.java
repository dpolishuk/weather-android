package io.dp.weather.app;

import android.location.Geocoder;
import com.google.gson.Gson;
import com.squareup.otto.Bus;
import dagger.Component;
import io.dp.weather.app.activity.ActivityModule;
import io.dp.weather.app.activity.ActivitySubcomponent;
import io.dp.weather.app.net.PlacesApi;
import io.dp.weather.app.net.WeatherApi;
import javax.inject.Singleton;

/**
 * Created by deepol on 19/08/15.
 */
@Singleton @Component(modules = AppModule.class) public interface AppComponent {
  void inject(WeatherApplication application);

  Gson provideGson();

  Bus provideBus();

  Geocoder proviceGeocoder();

  WeatherApi provideForecastApi();

  PlacesApi providePlacesApi();

  ActivitySubcomponent plus(ActivityModule module);
}
