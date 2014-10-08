package io.dp.weather.app.net;

import io.dp.weather.app.net.dto.Forecast;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by dp on 08/10/14.
 */
public interface WeatherApi {

  @GET("/weather.ashx")
  public Observable<Forecast> getForecast(@Query("q") String params, @Query("num_of_days") int days);
}
