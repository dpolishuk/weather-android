package io.dp.weather.app;

import dagger.Subcomponent;
import io.dp.weather.app.activity.debug.DebugActivity;
import io.dp.weather.app.activity.debug.DebugBusModule;
import io.dp.weather.app.annotation.PerActivity;

/**
 * Created by deepol on 16/09/15.
 */
@PerActivity
@Subcomponent(modules = DebugBusModule.class)
public interface DebugBusSubcomponent extends BusSubcomponent {

  void inject(DebugActivity debugActivity);
}
