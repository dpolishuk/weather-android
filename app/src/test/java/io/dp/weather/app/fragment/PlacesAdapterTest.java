package io.dp.weather.app.fragment;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import io.dp.weather.app.Const;
import io.dp.weather.app.R;
import io.dp.weather.app.TestUtils;
import io.dp.weather.app.TestWeatherApplication;
import io.dp.weather.app.net.WeatherApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dp on 10/10/14.
 */

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class PlacesAdapterTest {

  @Inject
  SharedPreferences prefs;

  @Inject
  WeatherApi weatherApi;

  @Before
  public void setUp() throws Exception {
    ((TestWeatherApplication) Robolectric.application).getApplicationGraph().inject(this);
  }

  @Test
  public void testCelciusItems() throws Exception {
    prefs.edit().putBoolean(Const.USE_CELCIUS, true).commit();
    WeatherFragment f = new WeatherFragment();
    TestUtils.startWeatherFragment(f);
    assertNotNull(f);
    assertNotNull(f.adapter);

    View v = f.adapter.getView(0, null, null);
    TextView degreeType = (TextView) v.findViewById(R.id.degrees_type);
    assertNotNull(degreeType);
    assertEquals(Const.CELCIUS, degreeType.getText());
  }

  @Test
  public void testFahrenheitItems() throws Exception {
    prefs.edit().putBoolean(Const.USE_CELCIUS, false).commit();
    WeatherFragment f = new WeatherFragment();
    TestUtils.startWeatherFragment(f);
    assertNotNull(f);
    assertNotNull(f.adapter);

    View v = f.adapter.getView(0, null, null);
    TextView degreeType = (TextView) v.findViewById(R.id.degrees_type);
    assertNotNull(degreeType);
    assertEquals(Const.FAHRENHEIT, degreeType.getText());
  }
}
