package io.dp.weather.app;

import android.location.Geocoder;
import com.google.gson.Gson;
import com.squareup.otto.Bus;
import dagger.Component;
import dagger.Provides;
import io.dp.weather.app.annotation.IOScheduler;
import io.dp.weather.app.annotation.UIScheduler;
import io.dp.weather.app.net.PlacesApi;
import io.dp.weather.app.net.WeatherApi;
import javax.inject.Singleton;
import rx.Scheduler;

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

  @UIScheduler Scheduler provideUiScheduler();

  @IOScheduler Scheduler provideIoScheduler();
}
