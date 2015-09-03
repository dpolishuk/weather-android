package io.dp.weather.app.adapter;

import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import io.dp.weather.app.R;
import io.dp.weather.app.net.PlacesApi;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created by dp on 09/10/14.
 */
public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

  private ArrayList<String> resultList;
  private PlacesApi placesApi;

  @Inject
  public PlacesAutoCompleteAdapter(RxAppCompatActivity activity, PlacesApi placesApi) {
    super(activity, R.layout.item_search_list);
    this.placesApi = placesApi;
  }

  @Override
  public int getCount() {
    return resultList.size();
  }

  @Override
  public String getItem(int index) {
    return resultList.get(index);
  }

  @Override
  public Filter getFilter() {
    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint != null) {
          JsonObject jsonResults = placesApi.getAutocomplete(constraint.toString());

          // Create a JSON object hierarchy from the results
          JsonArray predsJsonArray = jsonResults.getAsJsonArray("predictions");

          // Extract the Place descriptions from the results
          resultList = new ArrayList<String>(predsJsonArray.size());
          for (int i = 0; i < predsJsonArray.size(); i++) {
            resultList.add(
                ((JsonObject) predsJsonArray.get(i)).get("description").getAsString());
          }

          // Assign the data to the FilterResults
          filterResults.values = resultList;
          filterResults.count = resultList.size();
        }
        return filterResults;
      }

      @Override
      protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results != null && results.count > 0) {
          notifyDataSetChanged();
        } else {
          notifyDataSetInvalidated();
        }
      }
    };
  }
}
