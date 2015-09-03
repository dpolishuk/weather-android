package io.dp.weather.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.annotation.CachePrefs;
import io.dp.weather.app.annotation.ConfigPrefs;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.net.PlacesApi;
import io.dp.weather.app.net.WeatherApi;
import io.dp.weather.app.net.dto.Forecast;
import javax.inject.Singleton;
import retrofit.MockRestAdapter;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Query;
import rx.Observable;
import rx.Subscriber;

import static org.mockito.Mockito.mock;

/**
 * Created by dp on 08/10/14.
 */

@Module
public class MockAppModule extends AppModule {

  public MockAppModule(WeatherApplication application) {
    super(application);
  }

  @Provides
  @Singleton
  public Application provideApplication() {
    return application;
  }

  @Provides
  @Singleton
  public WeatherApi provideForecastApi(Gson gson) {
    RestAdapter.Builder b = new RestAdapter.Builder();

    if (BuildConfig.DEBUG) {
      b.setLogLevel(RestAdapter.LogLevel.FULL);
    }

    b.setRequestInterceptor(new RequestInterceptor() {
      @Override
      public void intercept(RequestFacade request) {
        request.addQueryParam("key", BuildConfig.FORECAST_API_KEY);
        request.addQueryParam("format", "json");
      }
    });

    RestAdapter
        restAdapter = b.setEndpoint(BuildConfig.FORECAST_API_URL).build();

    MockRestAdapter mock = MockRestAdapter.from(restAdapter);

    Forecast f = gson.fromJson(TestUtils.WEATHER_JSON, Forecast.class);
    return mock.create(WeatherApi.class, new MockWeatherApi(f));
  }

  @Provides
  @Singleton
  public PlacesApi providePlacesApi() {
    RestAdapter.Builder b = new RestAdapter.Builder();

    if (BuildConfig.DEBUG) {
      b.setLogLevel(RestAdapter.LogLevel.FULL);
    }

    b.setRequestInterceptor(new RequestInterceptor() {
      @Override
      public void intercept(RequestFacade request) {
        request.addQueryParam("key", BuildConfig.PLACES_API_KEY);
        request.addQueryParam("sensor", "false");
      }
    });

    RestAdapter
        restAdapter = b.setEndpoint(BuildConfig.PLACES_API_URL).build();
    return restAdapter.create(PlacesApi.class);
  }

  @Provides
  @Singleton
  public Gson provideGson() {
    return new GsonBuilder().create();
  }

  @Provides
  @Singleton
  public Bus provideBus() {
    return new Bus();
  }

  @Provides
  @Singleton
  public Geocoder provideGeocoder() {
    return mock(Geocoder.class);
  }

  @Provides
  @ConfigPrefs
  public SharedPreferences provideConfigPrefs() {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }

  @Provides
  @CachePrefs
  public SharedPreferences provideCachePrefs() {
    return application.getSharedPreferences("cachePrefs", Context.MODE_PRIVATE);
  }

  @Provides
  public DatabaseHelper provideDatabaseHelper(Application app) {
    return new DatabaseHelper(app);
  }

  private class MockWeatherApi implements WeatherApi {

    Forecast forecast;

    public MockWeatherApi(Forecast forecast) {
      this.forecast = forecast;
    }

    @Override
    public Observable<Forecast> getForecast(@Query("q") String params,
                                            @Query("num_of_days") int days) {
      return Observable.create(new Observable.OnSubscribe<Forecast>() {
        @Override
        public void call(Subscriber<? super Forecast> subscriber) {
          subscriber.onNext(forecast);
          subscriber.onCompleted();
        }
      });
    }
  }
}
