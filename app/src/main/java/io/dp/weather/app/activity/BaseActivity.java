package io.dp.weather.app.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import dagger.ObjectGraph;
import io.dp.weather.app.WeatherApplication;

/**
 * Created by dp on 07/10/14.
 */
public class BaseActivity extends SherlockFragmentActivity {

  private ObjectGraph activityGraph;
  private ActivityModule activityModule;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    WeatherApplication application = (WeatherApplication) getApplication();
    activityModule = new ActivityModule(this);
    activityGraph = application.getApplicationGraph().plus(activityModule);

    // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
    activityGraph.inject(this);

    super.onCreate(savedInstanceState);
  }
}
