package io.dp.weather.app.activity.debug;

import dagger.Component;
import io.dp.weather.app.AppComponent;
import io.dp.weather.app.activity.ActivityModule;
import io.dp.weather.app.activity.BaseActivityComponent;
import io.dp.weather.app.annotation.PerActivity;

/**
 * Created by deepol on 04/09/15.
 */
@PerActivity @Component(
    modules = {
        ActivityModule.class, DebugBusModule.class
    }, dependencies = AppComponent.class)
public interface DebugActivityComponent
    extends BaseActivityComponent {

  void inject(DebugActivity debugActivity);
}
