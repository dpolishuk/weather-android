package io.dp.weather.app.net;

import io.dp.weather.app.net.dto.Forecast;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by dp on 07/10/14.
 */
public interface ForecastApi {

  @GET("/{lat},{lon}")
  public Observable<Forecast> getForecast(@Path("lat") float lat, @Path("lon") float lon);
}
