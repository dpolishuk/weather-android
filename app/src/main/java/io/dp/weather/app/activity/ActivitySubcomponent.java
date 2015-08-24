package io.dp.weather.app.activity;

import dagger.Subcomponent;
import io.dp.weather.app.annotation.PerActivity;
import io.dp.weather.app.fragment.FragmentModule;
import io.dp.weather.app.fragment.FragmentSubcomponent;

/**
 * Created by deepol on 19/08/15.
 */
@PerActivity @Subcomponent(modules = ActivityModule.class)
public interface ActivitySubcomponent {

  FragmentSubcomponent plus(FragmentModule module);
}
