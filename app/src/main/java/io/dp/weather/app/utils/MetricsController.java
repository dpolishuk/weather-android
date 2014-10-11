package io.dp.weather.app.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

import io.dp.weather.app.Const;
import io.dp.weather.app.annotation.ConfigPrefs;

/**
 * Created by dp on 11/10/14.
 */
public class MetricsController {

  private final SharedPreferences prefs;

  @Inject
  public MetricsController(@ConfigPrefs SharedPreferences prefs) {
    this.prefs = prefs;
  }

  public boolean useCelsius() {
    return prefs.getBoolean(Const.USE_CELCIUS, false);
  }

  public boolean useKmph() {
    return prefs.getBoolean(Const.USE_KMPH, false);
  }

  public boolean useMmhg() {
    return prefs.getBoolean(Const.USE_MMHG, false);
  }
}
