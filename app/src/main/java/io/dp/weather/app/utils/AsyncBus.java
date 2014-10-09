package io.dp.weather.app.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by dp on 09/10/14.
 */
public class AsyncBus extends Bus {

  private final Handler mainThread = new Handler(Looper.getMainLooper());

  public AsyncBus(ThreadEnforcer enforcer) {
    super(enforcer);
  }

  @Override
  public void post(final Object event) {
    mainThread.post(new Runnable() {
      @Override
      public void run() {
        AsyncBus.super.post(event);
      }
    });
  }

  public void postDelayed(final Object event, long delayMs) {
    mainThread.postDelayed(new Runnable() {
      @Override
      public void run() {
        AsyncBus.super.post(event);
      }
    }, delayMs);
  }
}
