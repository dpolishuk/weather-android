package io.dp.weather.app.adapter;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.dp.weather.app.Const;
import io.dp.weather.app.R;
import io.dp.weather.app.WeatherIconUrl;
import io.dp.weather.app.db.OrmliteCursorAdapter;
import io.dp.weather.app.db.table.Place;
import io.dp.weather.app.event.DeletePlaceEvent;
import io.dp.weather.app.net.WeatherApi;
import io.dp.weather.app.net.dto.CurrentCondition;
import io.dp.weather.app.net.dto.Forecast;
import io.dp.weather.app.net.dto.WeatherDesc;
import io.dp.weather.app.widget.WeatherFor5DaysView;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.observables.AndroidObservable;
import timber.log.Timber;

/**
 * Created by dp on 08/10/14.
 */
public class PlacesAdapter extends OrmliteCursorAdapter<Place> {

  private final Activity activity;
  private final Gson gson;
  private final WeatherApi api;

  private final LayoutInflater inflater;
  private final SharedPreferences prefs;

  private final Bus bus;

  private final LruCache<Long, Forecast> cache = new LruCache<Long, Forecast>(16);

  private final Scheduler uiScheduler;
  private final Scheduler ioScheduler;

  @Inject
  public PlacesAdapter(Activity activity, Gson gson, WeatherApi api, Bus bus,
                       @Named("uiScheduler") Scheduler uiScheduler,
                       @Named("ioScheduler") Scheduler ioScheduler) {
    super(activity, null, null);

    this.activity = activity;
    this.inflater = LayoutInflater.from(activity);
    this.gson = gson;
    this.api = api;
    this.prefs = activity.getPreferences(Context.MODE_PRIVATE);
    this.bus = bus;
    this.uiScheduler = uiScheduler;
    this.ioScheduler = ioScheduler;
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
  public void bindView(View itemView, Context context, final Place place) {
    boolean useCelcius = prefs.getBoolean(Const.USE_CELCIUS, true);

    ViewHolder holder = (ViewHolder) itemView.getTag();

    final String hash = String.valueOf(place.hashCode());
    holder.cityName.setText(place.getName());
    holder.temperatureView.setText("");

    holder.menuView.setTag(place.getId());
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

    long lastRequestTime = prefs.getLong(hash + "_time", -1);
    Timber.v("Last request time for " + hash + " was " + lastRequestTime);
    if (lastRequestTime == -1 || (lastRequestTime > 0
                                  && (System.currentTimeMillis() - lastRequestTime)
                                     > DateUtils.DAY_IN_MILLIS)) {
      Timber.v("Tryting to get forecast for " + hash);

      holder.progressView.setVisibility(View.VISIBLE);
      holder.contentView.setVisibility(View.GONE);

      String rawForecast = prefs.getString(hash, null);

      if (!TextUtils.isEmpty(hash)) {
        if (TextUtils.isEmpty(rawForecast)) {
          Double lat = place.getLat();
          Double lon = place.getLon();
          AndroidObservable
              .bindActivity(activity, api.getForecast(lat + "," + lon, Const.FORECAST_FOR_DAYS))
              .subscribeOn(ioScheduler)
              .observeOn(uiScheduler)
              .subscribe(new ForecastCacheSubscriber(hash));
        }
      }
    } else {
      holder.progressView.setVisibility(View.GONE);
      holder.contentView.setVisibility(View.VISIBLE);

      Forecast f = cache.get(place.getId());
      if (f == null) {
        String rawForecast = prefs.getString(hash, null);
        f = gson.fromJson(rawForecast, Forecast.class);
        cache.put(place.getId(), f);
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
          holder.degreeTypeView.setText(Const.CELCIUS);
        } else {
          holder.temperatureView.setText(condition.getTempF());
          holder.temperatureView.setText(Const.FAHRENHEIT);
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

  private class ForecastCacheSubscriber extends Subscriber<Forecast> {

    private String hash;

    public ForecastCacheSubscriber(String hash) {
      this.hash = hash;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(Forecast forecast) {
      prefs.edit().putLong(hash + "_time", System.currentTimeMillis()).apply();
      prefs.edit().putString(hash, gson.toJson(forecast))
          .apply();
      notifyDataSetChanged();
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
