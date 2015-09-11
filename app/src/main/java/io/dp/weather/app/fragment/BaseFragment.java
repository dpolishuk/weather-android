package io.dp.weather.app.fragment;

import android.os.Bundle;
import com.trello.rxlifecycle.components.support.RxFragment;
import io.dp.weather.app.activity.BaseActivityComponent;
import io.dp.weather.app.activity.HasComponent;

/**
 * Created by dp on 08/10/14.
 */
public abstract class BaseFragment extends RxFragment {

  private BaseActivityComponent component;

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    HasComponent<BaseActivityComponent> activity =
        (HasComponent<BaseActivityComponent>) getActivity();
    this.component = activity.getComponent();
  }

  public BaseActivityComponent getComponent() {
    return component;
  }
}
