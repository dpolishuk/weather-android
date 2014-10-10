package io.dp.weather.app.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import io.dp.weather.app.R;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On handset devices,
 * settings are presented as a single list. On tablets, settings are split by category, with
 * category headers shown to the left of the list of settings. <p> See <a
 * href="http://developer.android.com/design/patterns/settings.html"> Android Design: Settings</a>
 * for design guidelines and the <a href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity {

  /**
   * Determines whether to always show the simplified settings UI, where settings are presented in a
   * single list. When false, settings are shown as a master/detail two-pane view on tablets. When
   * true, a single pane is shown on tablets.
   */
  private static final boolean ALWAYS_SIMPLE_PREFS = false;


  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    setupSimplePreferencesScreen();
  }

  /**
   * Shows the simplified settings UI if the device configuration if the device configuration
   * dictates that a simplified, single-pane UI should be shown.
   */
  private void setupSimplePreferencesScreen() {
    if (!isSimplePreferences(this)) {
      return;
    }

    // In the simplified UI, fragments are not used at all and we instead
    // use the older PreferenceActivity APIs.

    // Add 'general' preferences.
    addPreferencesFromResource(R.xml.pref_general);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean onIsMultiPane() {
    return isXLargeTablet(this) && !isSimplePreferences(this);
  }

  /**
   * Helper method to determine if the device has an extra-large screen. For example, 10" tablets
   * are extra-large.
   */
  private static boolean isXLargeTablet(Context context) {
    return (context.getResources().getConfiguration().screenLayout
            & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
  }

  /**
   * Determines whether the simplified settings UI should be shown. This is true if this is forced
   * via {@link #ALWAYS_SIMPLE_PREFS}, or the device doesn't have newer APIs like {@link
   * PreferenceFragment}, or the device doesn't have an extra-large screen. In these cases, a
   * single-pane "simplified" settings UI should be shown.
   */
  private static boolean isSimplePreferences(Context context) {
    return ALWAYS_SIMPLE_PREFS
           || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
           || !isXLargeTablet(context);
  }

  /**
   * This fragment shows general preferences only. It is used when the activity is showing a
   * two-pane settings UI.
   */
  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  public static class GeneralPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.pref_general);
    }
  }
}
