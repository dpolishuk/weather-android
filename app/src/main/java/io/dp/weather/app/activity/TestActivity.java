package io.dp.weather.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.dp.weather.app.R;
import io.dp.weather.app.adapter.PlacesAutoCompleteAdapter;

/**
 * Created by dp on 09/10/14.
 */
public class TestActivity extends BaseActivity implements AdapterView.OnItemClickListener {

  @InjectView(R.id.search_box)
  AutoCompleteTextView searchBoxView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    ButterKnife.inject(this);

    searchBoxView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.item_search_list));
    searchBoxView.setOnItemClickListener(this);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

  }
}
