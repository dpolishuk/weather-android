package io.dp.weather.app;

import com.trello.rxlifecycle.components.ActivityLifecycleProvider;
import io.dp.weather.app.annotation.IOSched;
import io.dp.weather.app.annotation.PerActivity;
import io.dp.weather.app.annotation.UISched;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by deepol on 24/08/15.
 */
@PerActivity
public class SchedulersManager {

  private final Scheduler ioScheduler;
  private final Scheduler uiScheduler;

  @Inject
  public SchedulersManager(@IOSched Scheduler ioScheduler, @UISched Scheduler uiScheduler) {
    this.ioScheduler = ioScheduler;
    this.uiScheduler = uiScheduler;
  }

  public <T> Observable.Transformer<T, T> applySchedulers(ActivityLifecycleProvider provider) {
    return observable -> ((Observable) observable).subscribeOn(ioScheduler)
        .observeOn(uiScheduler)
        .compose(provider.bindToLifecycle());
  }
}
