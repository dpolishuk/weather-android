package io.dp.weather.app.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by deepol on 11/09/15.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface UISched {
}
