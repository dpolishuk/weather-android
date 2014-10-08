package io.dp.weather.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Application;
import android.os.StrictMode;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.net.WeatherApi;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import timber.log.Timber;

/**
 * Created by dp on 07/10/14.
 */
@Module(library = true)
public class AppModule {

  private Application application;

  public AppModule(Application application) {
    this.application = application;

    Timber.plant(new Timber.DebugTree());

    strictMode();
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
        request.addQueryParam("key", "499cb12ae4fc7740adee2fa7b2007982f75338c0");
        request.addQueryParam("format", "json");
      }
    });

    RestAdapter
        restAdapter = b.setEndpoint(BuildConfig.FORECAST_API_URL).build();
    return restAdapter.create(WeatherApi.class);
  }

  @Provides
  @Singleton
  public Gson provideGson() {
    return new GsonBuilder().create();
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
