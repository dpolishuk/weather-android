package io.dp.weather.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.etsy.android.grid.StaggeredGridView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.trello.rxlifecycle.components.ActivityLifecycleProvider;
import io.dp.weather.app.BusSubcomponent;
import io.dp.weather.app.R;
import io.dp.weather.app.SchedulersManager;
import io.dp.weather.app.activity.ActivityComponent;
import io.dp.weather.app.activity.SettingsActivity;
import io.dp.weather.app.activity.debug.DebugActivity;
import io.dp.weather.app.adapter.PlacesAdapter;
import io.dp.weather.app.adapter.PlacesAutoCompleteAdapter;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.db.OrmliteCursorLoader;
import io.dp.weather.app.db.Queries;
import io.dp.weather.app.db.table.Place;
import io.dp.weather.app.event.AddPlaceEvent;
import io.dp.weather.app.event.DeletePlaceEvent;
import io.dp.weather.app.event.UpdateListEvent;
import io.dp.weather.app.utils.Observables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observer;
import rx.Subscription;

public class WeatherFragment extends BaseFragment
    implements LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener,
    Observer<Place> {

  List<Subscription> subscriptionList = new ArrayList<Subscription>();

  @Inject Geocoder geocoder;

  @Inject PlacesAdapter adapter;

  @Inject DatabaseHelper dbHelper;

  @Inject Bus bus;

  @Inject PlacesAutoCompleteAdapter placesAutoCompleteAdapter;

  @Inject SchedulersManager schedulersManager;

  @InjectView(R.id.grid) StaggeredGridView gridView;

  @InjectView(R.id.swipe_layout) SwipeRefreshLayout swipeRefreshView;

  public static WeatherFragment newInstance() {
    return new WeatherFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.fragment_weather, container, false);
    ButterKnife.inject(this, v);

    swipeRefreshView.setOnRefreshListener(this);

    return v;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ((BusSubcomponent) getComponent()).inject(this);

    setRetainInstance(true);

    adapter.setQuery(Queries.prepareCityQuery(dbHelper));
    gridView.setAdapter(adapter);

    getLoaderManager().restartLoader(0, null, this);

    setHasOptionsMenu(true);
  }

  @Override public void onResume() {
    super.onResume();
    bus.register(this);

    adapter.notifyDataSetChanged();
  }

  @Override public void onPause() {
    super.onPause();
    bus.unregister(this);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);

    inflater.inflate(R.menu.main, menu);

    final MenuItem addItem = menu.findItem(R.id.action_add);

    final AutoCompleteTextView addView = (AutoCompleteTextView) addItem.getActionView();
    MenuItemCompat.setOnActionExpandListener(addItem, new MenuItemCompat.OnActionExpandListener() {
      @Override public boolean onMenuItemActionExpand(MenuItem item) {
        addView.post(() -> {
          addView.requestFocus();
          InputMethodManager imm =
              (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
          imm.showSoftInput(addView, InputMethodManager.SHOW_IMPLICIT);
        });
        return true;
      }

      @Override public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
      }
    });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    switch (id) {
      case R.id.action_add:
        return true;

      case R.id.action_settings:
        startActivity(new Intent(getActivity(), SettingsActivity.class));
        return true;

      case R.id.action_debug:
        startActivity(new Intent(getActivity(), DebugActivity.class));
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();

    for (Subscription s : subscriptionList) {
      s.unsubscribe();
    }
  }

  @Override public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
    try {
      return new OrmliteCursorLoader<>(getActivity(), dbHelper.getPlaceDao(), adapter.getQuery());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
    adapter.changeCursor(cursor);
    gridView.post(() -> gridView.setSelection(gridView.getCount() - 1));
  }

  @Override public void onLoaderReset(Loader<Cursor> loader) {
    adapter.changeCursor(null);
  }

  @Override public void onRefresh() {
    swipeRefreshView.setRefreshing(true);
    adapter.clear();
    adapter.notifyDataSetChanged();
    swipeRefreshView.setRefreshing(false);
  }

  @Subscribe public void onUpdateList(UpdateListEvent event) {
    getLoaderManager().restartLoader(0, null, this);
  }

  @Subscribe public void onDeletePlace(DeletePlaceEvent event) {
    if (event.getId() != null) {
      try {
        dbHelper.getPlaceDao().deleteById(event.getId());
        getLoaderManager().restartLoader(0, null, this);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Subscribe public void onAddPlace(AddPlaceEvent event) {
    Subscription s = Observables.getGeoForPlace(getActivity(), dbHelper, geocoder, event.getLookupPlace())
        .compose(schedulersManager.applySchedulers((ActivityLifecycleProvider) getActivity()))
        .subscribe(WeatherFragment.this);

    subscriptionList.add(s);
  }

  @Override public void onCompleted() {

  }

  @Override public void onError(Throwable e) {

  }

  @Override public void onNext(Place place) {
    bus.post(new UpdateListEvent());
  }
}
