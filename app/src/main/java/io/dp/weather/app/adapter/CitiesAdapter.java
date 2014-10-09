package io.dp.weather.app.adapter;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.dp.weather.app.Const;
import io.dp.weather.app.R;
import io.dp.weather.app.WeatherIconUrl;
import io.dp.weather.app.db.OrmliteCursorAdapter;
import io.dp.weather.app.db.table.City;
import io.dp.weather.app.event.DeletePlaceEvent;
import io.dp.weather.app.net.WeatherApi;
import io.dp.weather.app.net.dto.CurrentCondition;
import io.dp.weather.app.net.dto.Forecast;
import io.dp.weather.app.net.dto.WeatherDesc;
import io.dp.weather.app.widget.WeatherFor5DaysView;
import rx.Observable;
import rx.Subscriber;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by dp on 08/10/14.
 */
public class CitiesAdapter extends OrmliteCursorAdapter<City> {

  private final Activity activity;
  private final Gson gson;
  private final Geocoder geocoder;
  private final WeatherApi api;

  private final LayoutInflater inflater;
  private final SharedPreferences prefs;

  private final Bus bus;

  private final LruCache<Long, Forecast> cache = new LruCache<Long, Forecast>(16);

  @Inject
  public CitiesAdapter(Activity activity, Gson gson, WeatherApi api, Bus bus) {
    super(activity, null, null);

    this.activity = activity;
    this.inflater = LayoutInflater.from(activity);
    this.gson = gson;
    this.geocoder = new Geocoder(activity);
    this.api = api;
    this.prefs = activity.getPreferences(Context.MODE_PRIVATE);
    this.bus = bus;
  }

  public void clear() {
    this.prefs.edit().clear().apply();
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
    View v = this.inflater.inflate(R.layout.item_city_weather, viewGroup, false);
    ViewHolder holder = new ViewHolder(v);
    v.setTag(holder);
    return v;
  }

  @Override
  public void bindView(View itemView, Context context, City city) {
    boolean useCelcius = prefs.getBoolean(Const.USE_CELCIUS, true);

    ViewHolder holder = (ViewHolder) itemView.getTag();

    final String name = city.getName();
    holder.cityName.setText(name);
    holder.temperatureView.setText("");

    holder.menuView.setTag(city.getId());
    holder.menuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Long id = (Long) v.getTag();

        PopupMenu popupMenu = new PopupMenu(activity, v);
        popupMenu.inflate(R.menu.item_place);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(MenuItem item) {
            bus.post(new DeletePlaceEvent(id));
            return true;
          }
        });

        popupMenu.show();
      }
    });

    long lastRequestTime = prefs.getLong(name + "_time", -1);
    Timber.v("Last request time for " + name + " was " + lastRequestTime);
    if (lastRequestTime == -1 || (lastRequestTime > 0
                                  && (System.currentTimeMillis() - lastRequestTime)
                                     > DateUtils.DAY_IN_MILLIS)) {
      Timber.v("Tryting to get forecast for " + name);

      holder.progressView.setVisibility(View.VISIBLE);
      holder.contentView.setVisibility(View.GONE);


      String rawForecast = prefs.getString(name, null);

      if (!TextUtils.isEmpty(name)) {
        if (!TextUtils.isEmpty(rawForecast)) {
          Forecast f = gson.fromJson(rawForecast, Forecast.class);
          Timber.v("!! Got forecast: " + f);
        } else {

          AndroidObservable.bindActivity(activity, Observable.create(
              new Observable.OnSubscribe<List<Address>>() {
                @Override
                public void call(Subscriber<? super List<Address>> subscriber) {
                  try {
                    synchronized (geocoder) {
                      List<Address> addresses = geocoder.getFromLocationName(name, 1);
                      subscriber.onNext(addresses);
                    }
                  } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                  } finally {
                    subscriber.onCompleted();
                  }
                }
              }
          )).flatMap(new Func1<List<Address>, Observable<Forecast>>() {
                       @Override
                       public Observable<Forecast> call(
                           List<Address> addresses) {
                         Timber.v("Got addresses: " + addresses);
                         if (addresses != null && addresses.size() > 0) {
                           Address address = addresses.get(0);
                           return api.getForecast(address.getLatitude() + "," +
                                                  address.getLongitude(), 5);
                         }

                         return null;
                       }
                     }
          ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
              new Subscriber<Forecast>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Forecast forecast) {
                  prefs.edit().putLong(name + "_time", System.currentTimeMillis()).apply();
                  prefs.edit().putString(name, gson.toJson(forecast))
                      .apply();
                  notifyDataSetChanged();
                }
              });
        }
      }
    } else {
      holder.progressView.setVisibility(View.GONE);
      holder.contentView.setVisibility(View.VISIBLE);

      Forecast f = cache.get(city.getId());
      if (f == null) {
        String rawForecast = prefs.getString(name, null);
        f = gson.fromJson(rawForecast, Forecast.class);
        cache.put(city.getId(), f);
      }

      List<CurrentCondition> conditions = f.getData().getCurrentCondition();
      if (conditions != null && conditions.size() > 0) {
        CurrentCondition condition = conditions.get(0);

        List<WeatherDesc> descList = condition.getWeatherDesc();
        if (descList != null && descList.size() > 0) {
          String description = descList.get(0).getValue();
          holder.weatherDescView.setText(description);
        }

        if (useCelcius) {
          holder.temperatureView.setText(condition.getTempC());
          holder.degreeTypeView.setText("C˚");
        } else {
          holder.temperatureView.setText(condition.getTempF());
          holder.temperatureView.setText("F˚");
        }

        List<WeatherIconUrl> urls = conditions.get(0).getWeatherIconUrl();

        if (urls != null && urls.size() > 0) {
          String url = urls.get(0).getValue();
          Picasso.with(activity).load(!TextUtils.isEmpty(url) ? url : null).into(
              holder.weatherState);
        }
      }

      holder.weatherFor5DaysView.setWeatherForWeek(f.getData().getWeather(), useCelcius);
    }
  }

  static class ViewHolder {

    @InjectView(R.id.weather_state)
    ImageView weatherState;
    @InjectView(R.id.city_name)
    TextView cityName;
    @InjectView(R.id.weather_for_week)
    WeatherFor5DaysView weatherFor5DaysView;
    @InjectView(R.id.temperature)
    TextView temperatureView;

    @InjectView(R.id.degrees_type)
    TextView degreeTypeView;

    @InjectView(R.id.weather_description)
    TextView weatherDescView;

    @InjectView(R.id.progress)
    ProgressBar progressView;

    @InjectView(R.id.content)
    View contentView;

    @InjectView(R.id.menu)
    View menuView;

    ViewHolder(View view) {
      ButterKnife.inject(this, view);
    }
  }
}
