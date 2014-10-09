package io.dp.weather.app.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import io.dp.weather.app.net.PlacesApi;

/**
 * Created by dp on 09/10/14.
 */
public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

  private ArrayList<String> resultList;

  public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
    super(context, textViewResourceId);
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
    Filter filter = new Filter() {
      @Override
      protected Filter.FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint != null) {
          // Retrieve the autocomplete results.
          resultList = PlacesApi.autocomplete(constraint.toString());

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
    return filter;
  }
}
