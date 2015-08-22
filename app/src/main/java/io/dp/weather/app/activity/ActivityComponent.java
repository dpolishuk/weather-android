package io.dp.weather.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.squareup.otto.Bus;
import dagger.Component;
import dagger.Provides;
import io.dp.weather.app.AppComponent;
import io.dp.weather.app.annotation.CachePrefs;
import io.dp.weather.app.annotation.ConfigPrefs;
import io.dp.weather.app.annotation.IOScheduler;
import io.dp.weather.app.annotation.PerActivity;
import io.dp.weather.app.annotation.UIScheduler;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.net.PlacesApi;
import io.dp.weather.app.net.WeatherApi;
import rx.Scheduler;

/**
 * Created by deepol on 19/08/15.
 */
@PerActivity @Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  Gson provideGson();

  Bus provideBus();

  Geocoder proviceGeocoder();

  Activity provideActivity();

  WeatherApi provideForecastApi();

  PlacesApi providePlacesApi();

  @ConfigPrefs SharedPreferences provideConfigPrefs();

  @CachePrefs SharedPreferences provideCachePrefs();

  @UIScheduler Scheduler provideUiScheduler();

  @IOScheduler Scheduler provideIoScheduler();

  DatabaseHelper provideDatabaseHelper();
}
