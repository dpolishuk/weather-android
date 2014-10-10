package io.dp.weather.app.net;

import com.google.gson.JsonObject;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by dp on 10/10/14.
 */
public interface PlacesApi {

  @GET("/place/autocomplete/json")
  JsonObject getAutocomplete(@Query("input") String input);
}
