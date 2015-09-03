package io.dp.weather.app;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by deepol on 24/08/15.
 */
@Singleton
public class SchedulersManager {

  private final Scheduler ioScheduler;
  private final Scheduler uiScheduler;

  @Inject
  public SchedulersManager() {
    this.ioScheduler = Schedulers.io();
    this.uiScheduler = AndroidSchedulers.mainThread();
  }

  public <T> Observable.Transformer<T, T> applySchedulers(RxAppCompatActivity activity) {
    return observable -> ((Observable) observable).subscribeOn(ioScheduler)
        .observeOn(uiScheduler)
        .compose(activity.bindToLifecycle());
  }
}
