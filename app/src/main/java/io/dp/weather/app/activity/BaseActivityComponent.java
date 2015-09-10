package io.dp.weather.app.activity;

import com.squareup.otto.Bus;

/**
 * Created by deepol on 04/09/15.
 */
public interface BaseActivityComponent {

  Bus provideBus();
}
