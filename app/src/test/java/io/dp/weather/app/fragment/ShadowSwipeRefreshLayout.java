package io.dp.weather.app.fragment;

import android.support.annotation.ColorRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.internal.Shadow;
import org.robolectric.shadows.ShadowViewGroup;

/**
 * Shadow for {@link android.support.v4.widget.SwipeRefreshLayout}
 */
@Implements(SwipeRefreshLayout.class)
public class ShadowSwipeRefreshLayout extends ShadowViewGroup {
  @RealObject SwipeRefreshLayout realObject;
  private OnRefreshListener listener;

  @Implementation
  public void setOnRefreshListener(OnRefreshListener listener) {
    this.listener = listener;
    Shadow.directlyOn(realObject, SwipeRefreshLayout.class).setOnRefreshListener(listener);
  }

  @Implementation
  public void setColorSchemeResources(@ColorRes int... colorResIds) {

  }

  /**
   * Non-Android accessor.
   *
   * @return OnRefreshListener that was previously set.
   */
  public OnRefreshListener getOnRefreshListener() {
    return listener;
  }
}
