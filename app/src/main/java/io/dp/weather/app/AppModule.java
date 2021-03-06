package io.dp.weather.app;

import android.app.Application;
import android.location.Geocoder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.annotation.IOSched;
import io.dp.weather.app.annotation.UISched;
import io.dp.weather.app.net.PlacesApi;
import io.dp.weather.app.net.WeatherApi;
import javax.inject.Singleton;
import retrofit.RestAdapter;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by dp on 07/10/14.
 */
@Module public class AppModule {

  protected final Application application;

  public AppModule(WeatherApp application) {
    this.application = application;

    Timber.plant(new Timber.DebugTree());
  }

  @Provides @Singleton public Geocoder proviceGeocoder() {
    return new Geocoder(application);
  }

  @Provides @Singleton public Application provideApplication() {
    return application;
  }

  @Provides @Singleton public WeatherApi provideForecastApi() {
    RestAdapter.Builder b = new RestAdapter.Builder();

    if (BuildConfig.DEBUG) {
      b.setLogLevel(RestAdapter.LogLevel.FULL);
    }

    b.setRequestInterceptor(request -> {
      request.addQueryParam("key", BuildConfig.FORECAST_API_KEY);
      request.addQueryParam("format", "json");
    });

    RestAdapter restAdapter = b.setEndpoint(BuildConfig.FORECAST_API_URL).build();
    return restAdapter.create(WeatherApi.class);
  }

  @Provides @Singleton public PlacesApi providePlacesApi() {
    RestAdapter.Builder b = new RestAdapter.Builder();

    if (BuildConfig.DEBUG) {
      b.setLogLevel(RestAdapter.LogLevel.FULL);
    }

    b.setRequestInterceptor(request -> {
      request.addQueryParam("key", BuildConfig.PLACES_API_KEY);
      request.addQueryParam("sensor", "false");
    });

    RestAdapter restAdapter = b.setEndpoint(BuildConfig.PLACES_API_URL).build();
    return restAdapter.create(PlacesApi.class);
  }

  @Provides @Singleton public Gson provideGson() {
    return new GsonBuilder().create();
  }

  @Provides @Singleton @IOSched public Scheduler provideIoScheduler() {
    return Schedulers.io();
  }

  @Provides @Singleton @UISched public Scheduler provideUiScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
