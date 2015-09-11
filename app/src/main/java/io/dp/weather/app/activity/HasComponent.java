package io.dp.weather.app.activity;

/**
 * Created by deepol on 11/09/15.
 */
public interface HasComponent<T> {

  T createComponent();

  T getComponent();
}
