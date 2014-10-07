package io.dp.weather.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.dp.weather.app.R;
import io.dp.weather.app.net.ForecastApi;
import io.dp.weather.app.net.dto.Forecast;
import rx.Subscriber;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import timber.log.Timber;


public class MainActivity extends BaseActivity {

  List<Subscription> subscriptionList = new ArrayList<Subscription>();

  @Inject
  ForecastApi forecastApi;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new PlaceholderFragment())
          .commit();
    }

    Subscription s = AndroidObservable.bindActivity(this,
                                                    forecastApi.getForecast(37.8267f, -122.423f))
        .subscribe(
            new Subscriber<Forecast>() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable e) {
                Timber.e(e, "Got error");
              }

              @Override
              public void onNext(Forecast forecast) {
                Timber.v("Got forecast: " + forecast);
              }
            });

    subscriptionList.add(s);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    for (Subscription s : subscriptionList) {
      s.unsubscribe();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.

    getSupportMenuInflater().inflate(R.menu.main, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }
}
