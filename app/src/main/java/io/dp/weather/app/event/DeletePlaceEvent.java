package io.dp.weather.app.event;

/**
 * Created by dp on 09/10/14.
 */
public class DeletePlaceEvent {

  private final Long id;

  public DeletePlaceEvent(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
