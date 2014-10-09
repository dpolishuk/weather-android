package io.dp.weather.app.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.etsy.android.grid.StaggeredGridView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.dp.weather.app.R;
import io.dp.weather.app.adapter.CitiesAdapter;
import io.dp.weather.app.adapter.PlacesAutoCompleteAdapter;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.db.OrmliteCursorLoader;
import io.dp.weather.app.db.Queries;
import io.dp.weather.app.db.table.City;
import io.dp.weather.app.event.DeletePlaceEvent;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dp on 08/10/14.
 */
public class WeatherFragment extends BaseFragment
    implements LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener {

  List<Subscription> subscriptionList = new ArrayList<Subscription>();

  @Inject
  CitiesAdapter adapter;

  @Inject
  DatabaseHelper dbHelper;

  @Inject
  Bus bus;

  @InjectView(R.id.grid)
  StaggeredGridView gridView;

  @InjectView(R.id.swipe_layout)
  SwipeRefreshLayout swipeRefreshView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.fragment_weather, container, false);
    ButterKnife.inject(this, v);

    swipeRefreshView.setOnRefreshListener(this);
    swipeRefreshView.setColorSchemeResources(R.color.refresh_color_0, R.color.refresh_color_1,
                                             R.color.refresh_color_2, R.color.refresh_color_3);

    return v;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    adapter.setQuery(Queries.prepareCityQuery(dbHelper));
    gridView.setAdapter(adapter);

    getLoaderManager().restartLoader(0, null, this);

    setHasOptionsMenu(true);
  }

  @Override
  public void onResume() {
    super.onResume();
    bus.register(this);
  }

  @Override
  public void onPause() {
    super.onPause();
    bus.unregister(this);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);

    inflater.inflate(R.menu.main, menu);

    final MenuItem addItem = menu.findItem(R.id.action_add);

    final AutoCompleteTextView addView = (AutoCompleteTextView) addItem.getActionView();
    addItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
      @Override
      public boolean onMenuItemActionExpand(MenuItem menuItem) {
        addView.post(new Runnable() {
          @Override
          public void run() {
            addView.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(addView, InputMethodManager.SHOW_IMPLICIT);
          }
        });
        return true;
      }

      @Override
      public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return true;
      }
    });

    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    addView.setLayoutParams(params);
    addView.setAdapter(new PlacesAutoCompleteAdapter(getActivity(), R.layout.item_search_list));
    addView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String str = (String) parent.getItemAtPosition(position);
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
        addItem.collapseActionView();
        addView.setText("");

        Subscription s = AndroidObservable.bindActivity(getActivity(), Observable.create(
            new Observable.OnSubscribe<Void>() {
              @Override
              public void call(Subscriber<? super Void> subscriber) {
                try {
                  dbHelper.getCityDao().create(new City(str, 0, 0));
                  subscriber.onNext(null);
                } catch (SQLException e) {
                  e.printStackTrace();
                  subscriber.onError(e);
                } finally {
                  subscriber.onCompleted();
                }

              }
            })).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
            new Subscriber<Void>() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable e) {

              }

              @Override
              public void onNext(Void aVoid) {

                getLoaderManager().restartLoader(0, null, WeatherFragment.this);
              }
            });

        subscriptionList.add(s);
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_add) {
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

  @Override
  public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

    try {
      return new OrmliteCursorLoader<City>(getActivity(), dbHelper.getCityDao(),
                                           adapter.getQuery());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
    adapter.changeCursor(cursor);
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
    adapter.changeCursor(null);
  }

  @Override
  public void onRefresh() {
    swipeRefreshView.setRefreshing(true);
    adapter.clear();
    adapter.notifyDataSetChanged();
    swipeRefreshView.setRefreshing(false);
  }

  @Subscribe
  public void onDeletePlace(DeletePlaceEvent event) {
    if (event.getId() != null) {
      try {
        dbHelper.getCityDao().deleteById(event.getId());
        getLoaderManager().restartLoader(0, null, this);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
