package io.dp.weather.app;

import android.app.Application;
import android.location.Geocoder;
import android.os.StrictMode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.net.PlacesApi;
import io.dp.weather.app.net.WeatherApi;
import io.dp.weather.app.utils.AsyncBus;
import javax.inject.Singleton;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import timber.log.Timber;

/**
 * Created by dp on 07/10/14.
 */
@Module
public class AppModule {

  protected final Application application;

  public AppModule(WeatherApplication application) {
    this.application = application;

    Timber.plant(new Timber.DebugTree());

    //if (!application.isTesting()) {
    //  strictMode();
    //}
  }

  @Provides
  @Singleton
  public Geocoder proviceGeocoder() {
    return new Geocoder(application);
  }

  @Provides
  @Singleton
  public Application provideApplication() {
    return application;
  }

  @Provides
  @Singleton
  public WeatherApi provideForecastApi() {
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
    return restAdapter.create(WeatherApi.class);
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
    return new AsyncBus(ThreadEnforcer.ANY);
  }

  private void strictMode() {
    if (BuildConfig.DEBUG) {
      Picasso.with(application).setLoggingEnabled(true);

      Timber.d("Strict mode is enabled");
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                     .detectAll()
                                     .penaltyLog()
                                     .build());
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                                 .detectAll()
                                 .penaltyLog()
                                 .build());
    }
  }
}
