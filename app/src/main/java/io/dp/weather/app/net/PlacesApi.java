package io.dp.weather.app.net;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by dp on 09/10/14.
 */
public class PlacesApi {

  private static final String LOG_TAG = "ExampleApp";

  private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
  private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
  private static final String OUT_JSON = "/json";

  private static final String API_KEY = "AIzaSyA0o8WdYc40tWfRrKimdAWHvaFwW8h-N8w";

  public static ArrayList<String> autocomplete(String input) {
    ArrayList<String> resultList = null;

    HttpURLConnection conn = null;
    StringBuilder jsonResults = new StringBuilder();
    try {
      StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
      sb.append("?sensor=false&key=" + API_KEY);
//      sb.append("&components=country:uk");
      sb.append("&input=" + URLEncoder.encode(input, "utf8"));

      URL url = new URL(sb.toString());
      conn = (HttpURLConnection) url.openConnection();
      InputStreamReader in = new InputStreamReader(conn.getInputStream());

      // Load the results into a StringBuilder
      int read;
      char[] buff = new char[1024];
      while ((read = in.read(buff)) != -1) {
        jsonResults.append(buff, 0, read);
      }
    } catch (MalformedURLException e) {
      Log.e(LOG_TAG, "Error processing Places API URL", e);
      return resultList;
    } catch (IOException e) {
      Log.e(LOG_TAG, "Error connecting to Places API", e);
      return resultList;
    } finally {
      if (conn != null) {
        conn.disconnect();
      }
    }

    try {
      // Create a JSON object hierarchy from the results
      JSONObject jsonObj = new JSONObject(jsonResults.toString());
      JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

      // Extract the Place descriptions from the results
      resultList = new ArrayList<String>(predsJsonArray.length());
      for (int i = 0; i < predsJsonArray.length(); i++) {
        resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
      }
    } catch (JSONException e) {
      Log.e(LOG_TAG, "Cannot process JSON results", e);
    }

    return resultList;
  }
}
