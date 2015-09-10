package io.dp.weather.app;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import dagger.Module;
import dagger.Provides;
import io.dp.weather.app.annotation.PerActivity;
import io.dp.weather.app.utils.AsyncBus;

/**
 * Created by deepol on 10/09/15.
 */
@Module
public class BusModule {

  @Provides
  @PerActivity
  public Bus provideBus() {
    return new AsyncBus(ThreadEnforcer.ANY, "default");
  }
}
