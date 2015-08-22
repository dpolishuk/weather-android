package io.dp.weather.app;

import org.robolectric.Robolectric;
import org.robolectric.TestLifecycleApplication;

import java.lang.reflect.Method;

/**
 * Created by dp on 08/10/14.
 */
public class TestWeatherApplication extends WeatherApplication implements TestLifecycleApplication {

  @Override
  public void beforeTest(Method method) {
  }

  @Override
  public void prepareTest(Object o) {

  }

  @Override
  public void afterTest(Method method) {

  }
}
