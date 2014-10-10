package io.dp.weather.app.widget;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.sql.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectViews;
import io.dp.weather.app.Const;
import io.dp.weather.app.R;
import io.dp.weather.app.WeatherIconUrl;
import io.dp.weather.app.net.dto.Weather;

/**
 * Created by dp on 08/10/14.
 */
public class WeatherFor5DaysView extends LinearLayout {

  @InjectViews({R.id.day_name_1, R.id.day_name_2, R.id.day_name_3, R.id.day_name_4, R.id.day_name_5})
  TextView[] dayNameViews;

  @InjectViews({R.id.day_1, R.id.day_2, R.id.day_3, R.id.day_4, R.id.day_5})
  ImageView[] dayViews;

  @InjectViews({R.id.temp_1, R.id.temp_2, R.id.temp_3, R.id.temp_4, R.id.temp_5})
  TextView[] tempViews;

  Transformation t;
  Context context;

  String celsius;
  String fahrenheit;

  public WeatherFor5DaysView(Context context) {
    super(context);
    init(context);
  }

  public WeatherFor5DaysView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    this.context = context;
    LayoutInflater.from(context).inflate(R.layout.view_weather_for_week, this, true);

    celsius = context.getString(R.string.celcius);
    fahrenheit = context.getString(R.string.fahrenheit);
  }

  public void setWeatherForWeek(List<Weather> weatherList, boolean useCelsius, Transformation t) {
    this.t = t;

    for (int i = 0; i < weatherList.size(); ++i) {
      ImageView v = dayViews[i];
      Weather weather = weatherList.get(i);

      try {
        Date date = Date.valueOf(weather.getDate());
        String weekDay = DateUtils.formatDateTime(getContext(), date.getTime(), DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_ABBREV_WEEKDAY);
        dayNameViews[i].setText(weekDay);
      } catch (IllegalArgumentException e) {
        dayNameViews[i].setText("");
      }

      if (useCelsius) {
        tempViews[i].setText(
            String.format("%s-%s" + context.getString(R.string.celcius), weather.getTempMinC(), weather.getTempMaxC()));
      } else {
        tempViews[i].setText(String.format("%s-%s" + context.getString(R.string.fahrenheit), weather.getTempMinF(),
                                           weather.getTempMaxF()));
      }

      List<WeatherIconUrl> urls = weather.getWeatherIconUrl();
      if (urls != null && urls.size() > 0) {
        WeatherIconUrl url = urls.get(0);
        if (!TextUtils.isEmpty(url.getValue())) {
          Picasso.with(getContext()).load(url.getValue()).transform(t).into(v);
        }
      }
    }
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

}
