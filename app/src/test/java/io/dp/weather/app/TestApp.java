package io.dp.weather.app;

import java.lang.reflect.Method;
import org.robolectric.TestLifecycleApplication;

/**
 * Created by deepol on 10/09/15.
 */
public class TestApp extends WeatherApp implements TestLifecycleApplication {
  @Override public AppComponent createComponent() {
    return DaggerMockAppComponent.builder().mockAppModule(new MockAppModule(this)).build();
  }

  @Override public void beforeTest(Method method) {

  }

  @Override public void prepareTest(Object test) {

  }

  @Override public void afterTest(Method method) {

  }
}
