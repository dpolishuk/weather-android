package io.dp.weather.app;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.fragment.WeatherFragmentTest;
import io.dp.weather.app.net.WeatherApi;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

import static org.mockito.Mockito.mock;

/**
 * Created by dp on 08/10/14.
 */

@Module(overrides = true, injects = {WeatherFragmentTest.class},
    library = true)
public class MockAppModule {

  Application application;

  public MockAppModule(Application application) {
    this.application = application;
  }

  @Singleton
  @Provides
  public WeatherApi provideRestApi() {

    RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://test.com").build();

    WeatherApi mockRestApi = mock(WeatherApi.class);

    MockRestAdapter restAdapter = MockRestAdapter.from(adapter);
    return restAdapter.create(WeatherApi.class, mockRestApi);
  }
}
