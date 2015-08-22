package io.dp.weather.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import io.dp.weather.app.activity.BaseActivity;

/**
 * Created by dp on 08/10/14.
 */
public abstract class BaseFragment extends Fragment {

  private FragmentComponent component;

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    BaseActivity activity = (BaseActivity) getActivity();
    this.component = DaggerFragmentComponent.builder()
        .activityComponent(activity.getComponent())
        .fragmentModule(new FragmentModule(this))
        .build();
  }

  public FragmentComponent getComponent() {
    return component;
  }
}
