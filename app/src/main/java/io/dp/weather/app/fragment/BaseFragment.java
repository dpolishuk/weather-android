package io.dp.weather.app.fragment;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragment;

import io.dp.weather.app.activity.BaseActivity;

/**
 * Created by dp on 08/10/14.
 */
public abstract class BaseFragment extends SherlockFragment {
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ((BaseActivity) getActivity()).inject(this);
  }
}
