package io.dp.weather.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.dp.weather.app.R;
import io.dp.weather.app.adapter.CitiesAdapter;
import rx.Subscription;

/**
 * Created by dp on 08/10/14.
 */
public class WeatherFragment extends BaseFragment {

  List<Subscription> subscriptionList = new ArrayList<Subscription>();

  @Inject
  CitiesAdapter adapter;

  @InjectView(R.id.grid)
  StaggeredGridView gridView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.fragment_weather, container, false);
    ButterKnife.inject(this, v);

    return v;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    CitiesAdapter.City london = new CitiesAdapter.City("London", 0, 0);
    CitiesAdapter.City barcelona = new CitiesAdapter.City("Barcelona", 0, 0);
    CitiesAdapter.City dublin = new CitiesAdapter.City("Dublin", 0, 0);
    CitiesAdapter.City moscow = new CitiesAdapter.City("Moscow", 0, 0);

    adapter.add(london);
    adapter.add(barcelona);
    adapter.add(dublin);
    adapter.add(moscow);

    gridView.setAdapter(adapter);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();

    for (Subscription s : subscriptionList) {
      s.unsubscribe();
    }
  }
}
