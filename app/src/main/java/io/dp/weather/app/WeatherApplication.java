package io.dp.weather.app;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by dp on 07/10/14.
 */
public class WeatherApplication extends Application {

  private ObjectGraph objectGraph;
  private AppModule appModule;

  @Override
  public void onCreate() {
    super.onCreate();

    appModule = new AppModule(this);

    objectGraph = ObjectGraph.create(getModules().toArray());

  }

  public AppModule getAppModule() {
    return appModule;
  }

  public List<Object> getModules() {
    List<Object> modules = new ArrayList<Object>();
    modules.add(appModule);
    return modules;
  }

  public ObjectGraph getApplicationGraph() {
    return objectGraph;
  }
}
