package io.dp.weather.app.fragment;

import android.location.Address;
import android.location.Geocoder;

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

import io.dp.weather.app.TestUtils;
import io.dp.weather.app.TestWeatherApplication;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.db.table.Place;
import io.dp.weather.app.event.AddPlaceEvent;
import io.dp.weather.app.net.WeatherApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class WeatherFragmentAddItemTest {

  @Inject
  WeatherApi weatherApi;

  @Inject
  DatabaseHelper dbHelper;

  @Inject
  Bus bus;

  @Inject
  Geocoder geocoder;

  @Before
  public void setUp() throws Exception {
    ((TestWeatherApplication) Robolectric.application).getApplicationGraph().inject(this);
  }

  @Test
  public void testAddPlaceFragment() throws Exception {
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

    List<Place> places = dbHelper.getPlaceDao().queryForEq(Place.NAME, placeName);

    assertEquals(1, places.size());
    assertEquals(placeName, places.get(0).getName());
  }

}