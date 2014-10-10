package io.dp.weather.app;

import org.robolectric.Robolectric;
import org.robolectric.TestLifecycleApplication;

import java.lang.reflect.Method;

import dagger.ObjectGraph;

/**
 * Created by dp on 08/10/14.
 */
public class TestWeatherApplication extends WeatherApplication implements TestLifecycleApplication {

  @Override
  protected void createObjectGraph() {
    System.setProperty("robolectric.logging","stderr");
    setObjectGraph(ObjectGraph.create(new MockAppModule(Robolectric.application)));
  }

  @Override
  public void beforeTest(Method method) {
//    System.setProperty("robolectric.logging","stdout");
  }

  @Override
  public void prepareTest(Object o) {

  }

  @Override
  public void afterTest(Method method) {

  }

  @Override
  protected boolean isTesting() {
    return true;
  }
}
