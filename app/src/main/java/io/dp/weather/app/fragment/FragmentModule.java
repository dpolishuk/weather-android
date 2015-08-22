package io.dp.weather.app.fragment;

import android.support.v4.app.Fragment;
import dagger.Module;

/**
 * Created by deepol on 19/08/15.
 */
@Module
public class FragmentModule {
  private final Fragment fragment;

  public FragmentModule(Fragment fragment) {
    this.fragment = fragment;
  }

}
