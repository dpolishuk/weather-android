package io.dp.weather.app.fragment;

import android.app.Application;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.TextView;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.dp.weather.app.Const;
import io.dp.weather.app.R;
import io.dp.weather.app.TestUtils;
import io.dp.weather.app.TestWeatherApplication;
import io.dp.weather.app.annotation.ConfigPrefs;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.db.FixedShadowSQLiteOpenHelper;
import io.dp.weather.app.db.table.Place;
import io.dp.weather.app.event.AddPlaceEvent;
import io.dp.weather.app.event.DeletePlaceEvent;
import io.dp.weather.app.net.WeatherApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dp on 10/10/14.
 */

@Config(emulateSdk = 18, shadows = FixedShadowSQLiteOpenHelper.class)
@RunWith(RobolectricTestRunner.class)
public class WeatherFragmentTest {

  @Inject
  WeatherApi weatherApi;

  @Inject
  Application app;

  @Inject
  Bus bus;

  @Inject
  Geocoder geocoder;

  @Inject
  DatabaseHelper databaseHelper;

  @Inject
  @ConfigPrefs
  SharedPreferences prefs;

  @Before
  public void setUp() throws Exception {
    ((TestWeatherApplication) Robolectric.application).getApplicationGraph().inject(this);
  }

  @Test
  public void testAddRemovePlaceFragment() throws Exception {
    WeatherFragment f = new WeatherFragment();
    TestUtils.startWeatherFragment(f);
    assertNotNull(f);
    assertNotNull(f.adapter);

    final String placeName = "Shanghai";

    Address address = mock(Address.class);
    when(address.getLatitude()).thenReturn(-1.0);
    when(address.getLongitude()).thenReturn(-1.0);

    List<Address> addresses = new ArrayList<Address>();
    addresses.add(address);

    try {
      when(geocoder.getFromLocationName(placeName, 1)).thenReturn(addresses);
    } catch (IOException e) {
      e.printStackTrace();
    }

    f.onAddPlace(new AddPlaceEvent(placeName));

    List<Place> places = databaseHelper.getPlaceDao().queryForEq(Place.NAME, placeName);

    assertEquals(1, places.size());
    assertEquals(placeName, places.get(0).getName());

    assertEquals(5, f.adapter.getCount());

    f.onDeletePlace(new DeletePlaceEvent(1L));
    f.adapter.notifyDataSetInvalidated();

    List<Place> placeList = databaseHelper.getPlaceDao().queryForAll();
    assertEquals(4, placeList.size());
    assertEquals(4, f.adapter.getCount());

  }

  @Test
  public void testMetrics() throws Exception {
    WeatherFragment f = new WeatherFragment();
    TestUtils.startWeatherFragment(f);
    assertNotNull(f);
    assertNotNull(f.adapter);

    {
      prefs.edit().putBoolean(Const.USE_CELCIUS, true).commit();

      View v = f.adapter.getView(0, null, null);
      TextView degreeType = (TextView) v.findViewById(R.id.degrees_type);
      assertNotNull(degreeType);
      assertEquals(app.getResources().getString(R.string.celcius), degreeType.getText());
    }

    {
      prefs.edit().putBoolean(Const.USE_CELCIUS, false).commit();

      View v = f.adapter.getView(0, null, null);
      TextView degreeType = (TextView) v.findViewById(R.id.degrees_type);
      assertNotNull(degreeType);
      assertEquals(app.getResources().getString(R.string.fahrenheit), degreeType.getText());
    }
  }
}
