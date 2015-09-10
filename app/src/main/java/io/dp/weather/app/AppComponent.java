package io.dp.weather.app;

import android.location.Geocoder;
import com.google.gson.Gson;
import dagger.Component;
import io.dp.weather.app.net.PlacesApi;
import io.dp.weather.app.net.WeatherApi;
import javax.inject.Singleton;

/**
 * Created by deepol on 19/08/15.
 */
@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

  Gson provideGson();

  Geocoder proviceGeocoder();

  WeatherApi provideForecastApi();

  PlacesApi providePlacesApi();
}
