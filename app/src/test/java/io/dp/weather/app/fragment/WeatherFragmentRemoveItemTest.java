package io.dp.weather.app.fragment;

import android.location.Geocoder;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import io.dp.weather.app.TestUtils;
import io.dp.weather.app.TestWeatherApplication;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.db.table.Place;
import io.dp.weather.app.event.DeletePlaceEvent;
import io.dp.weather.app.net.WeatherApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dp on 10/10/14.
 */

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class WeatherFragmentRemoveItemTest {

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
  public void testRemovePlaceFragment() throws Exception {
    WeatherFragment f = new WeatherFragment();
    TestUtils.startWeatherFragment(f);
    assertNotNull(f);
    assertNotNull(f.adapter);

    assertEquals(4, f.adapter.getCount());

    f.onDeletePlace(new DeletePlaceEvent(1L));
    f.adapter.notifyDataSetInvalidated();

    List<Place> placeList = dbHelper.getPlaceDao().queryForAll();
    assertEquals(3, placeList.size());
    assertEquals(3, f.adapter.getCount());
  }
}
