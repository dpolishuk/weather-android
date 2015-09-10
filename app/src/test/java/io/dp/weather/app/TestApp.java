package io.dp.weather.app;

import java.lang.reflect.Method;
import org.robolectric.TestLifecycleApplication;

/**
 * Created by deepol on 10/09/15.
 */
public class TestApp extends WeatherApplication implements TestLifecycleApplication {
  AppComponent component;

  @Override public AppComponent createComponent() {
    return null;
  }

  public AppComponent getComponent() {
    return component;
  }

  @Override public void beforeTest(Method method) {

  }

  @Override public void prepareTest(Object test) {

  }

  @Override public void afterTest(Method method) {

  }
}
