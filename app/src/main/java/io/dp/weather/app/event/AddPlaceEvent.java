package io.dp.weather.app.event;

/**
 * Created by dp on 10/10/14.
 */
public class AddPlaceEvent {

  private String lookupPlace;

  public AddPlaceEvent(String lookupPlace) {
    this.lookupPlace = lookupPlace;
  }

  public String getLookupPlace() {
    return lookupPlace;
  }
}
