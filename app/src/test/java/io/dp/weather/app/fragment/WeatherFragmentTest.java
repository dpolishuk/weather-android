package io.dp.weather.app.fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import io.dp.weather.app.TestWeatherApplication;
import io.dp.weather.app.net.WeatherApi;
import io.dp.weather.app.net.dto.Forecast;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class WeatherFragmentTest {

  @Inject
  WeatherApi weatherApi;

  @Before
  public void setUp() throws Exception {
    ((TestWeatherApplication) Robolectric.application).getApplicationGraph().inject(this);
  }

  @Test
  public void testGetForecast() throws Exception {

//    when(weatherApi.getForecast("33.22.22,11", 5)).thenReturn(Observable.<Forecast>empty());
//
//    weatherApi.getForecast("33.22.22,11", 5).observeOn(Schedulers.immediate()).subscribeOn(Schedulers.immediate()).subscribe(
//        new Subscriber<Forecast>() {
//          @Override
//          public void onCompleted() {
//
//          }
//
//          @Override
//          public void onError(Throwable e) {
//
//          }
//
//          @Override
//          public void onNext(Forecast forecast) {
//            System.out.println("Got forecast: " + forecast);
//          }
//        });
  }
}