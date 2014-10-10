package io.dp.weather.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.robolectric.Robolectric;

import io.dp.weather.app.activity.MainActivity;

/**
 * Created by dp on 10/10/14.
 */
public class TestUtils {

  public static final String
      WEATHER_JSON =
      "{ \"data\": { \"current_condition\": [ {\"cloudcover\": \"50\", \"humidity\": \"72\", \"observation_time\": \"12:29 PM\", \"precipMM\": \"0.4\", \"pressure\": \"1004\", \"temp_C\": \"13\", \"temp_F\": \"55\", \"visibility\": \"10\", \"weatherCode\": \"116\",  \"weatherDesc\": [ {\"value\": \"Partly Cloudy\" } ],  \"weatherIconUrl\": [ {\"value\": \"http:\\/\\/cdn.worldweatheronline.net\\/images\\/wsymbols01_png_64\\/wsymbol_0002_sunny_intervals.png\" } ], \"winddir16Point\": \"WSW\", \"winddirDegree\": \"250\", \"windspeedKmph\": \"17\", \"windspeedMiles\": \"11\" } ],  \"request\": [ {\"query\": \"Lat 53.34 and Lon -6.27\", \"type\": \"LatLon\" } ],  \"weather\": [ {\"date\": \"2014-10-10\", \"precipMM\": \"1.9\", \"tempMaxC\": \"14\", \"tempMaxF\": \"56\", \"tempMinC\": \"8\", \"tempMinF\": \"46\", \"weatherCode\": \"113\",  \"weatherDesc\": [ {\"value\": \"Sunny\" } ],  \"weatherIconUrl\": [ {\"value\": \"http:\\/\\/cdn.worldweatheronline.net\\/images\\/wsymbols01_png_64\\/wsymbol_0001_sunny.png\" } ], \"winddir16Point\": \"SW\", \"winddirDegree\": \"227\", \"winddirection\": \"SW\", \"windspeedKmph\": \"19\", \"windspeedMiles\": \"12\" }, {\"date\": \"2014-10-11\", \"precipMM\": \"3.9\", \"tempMaxC\": \"15\", \"tempMaxF\": \"59\", \"tempMinC\": \"6\", \"tempMinF\": \"43\", \"weatherCode\": \"176\",  \"weatherDesc\": [ {\"value\": \"Patchy rain nearby\" } ],  \"weatherIconUrl\": [ {\"value\": \"http:\\/\\/cdn.worldweatheronline.net\\/images\\/wsymbols01_png_64\\/wsymbol_0009_light_rain_showers.png\" } ], \"winddir16Point\": \"W\", \"winddirDegree\": \"261\", \"winddirection\": \"W\", \"windspeedKmph\": \"19\", \"windspeedMiles\": \"12\" }, {\"date\": \"2014-10-12\", \"precipMM\": \"0.4\", \"tempMaxC\": \"16\", \"tempMaxF\": \"61\", \"tempMinC\": \"6\", \"tempMinF\": \"42\", \"weatherCode\": \"113\",  \"weatherDesc\": [ {\"value\": \"Sunny\" } ],  \"weatherIconUrl\": [ {\"value\": \"http:\\/\\/cdn.worldweatheronline.net\\/images\\/wsymbols01_png_64\\/wsymbol_0001_sunny.png\" } ], \"winddir16Point\": \"WSW\", \"winddirDegree\": \"241\", \"winddirection\": \"WSW\", \"windspeedKmph\": \"12\", \"windspeedMiles\": \"8\" }, {\"date\": \"2014-10-13\", \"precipMM\": \"0.0\", \"tempMaxC\": \"15\", \"tempMaxF\": \"60\", \"tempMinC\": \"5\", \"tempMinF\": \"41\", \"weatherCode\": \"113\",  \"weatherDesc\": [ {\"value\": \"Sunny\" } ],  \"weatherIconUrl\": [ {\"value\": \"http:\\/\\/cdn.worldweatheronline.net\\/images\\/wsymbols01_png_64\\/wsymbol_0001_sunny.png\" } ], \"winddir16Point\": \"W\", \"winddirDegree\": \"262\", \"winddirection\": \"W\", \"windspeedKmph\": \"11\", \"windspeedMiles\": \"7\" }, {\"date\": \"2014-10-14\", \"precipMM\": \"0.0\", \"tempMaxC\": \"14\", \"tempMaxF\": \"58\", \"tempMinC\": \"10\", \"tempMinF\": \"49\", \"weatherCode\": \"113\",  \"weatherDesc\": [ {\"value\": \"Sunny\" } ],  \"weatherIconUrl\": [ {\"value\": \"http:\\/\\/cdn.worldweatheronline.net\\/images\\/wsymbols01_png_64\\/wsymbol_0001_sunny.png\" } ], \"winddir16Point\": \"SE\", \"winddirDegree\": \"133\", \"winddirection\": \"SE\", \"windspeedKmph\": \"12\", \"windspeedMiles\": \"7\" } ] }}";

  public static void startWeatherFragment(Fragment f) {
    MainActivity activity = Robolectric.buildActivity(MainActivity.class)
        .create()
        .start()
        .resume()
        .get();

    FragmentManager fragmentManager = activity.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(f, null);
    fragmentTransaction.commit();
  }
}
