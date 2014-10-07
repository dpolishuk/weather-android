package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

public class Forecast {

  @Expose
  private Double latitude;
  @Expose
  private Double longitude;
  @Expose
  private String timezone;
  @Expose
  private Integer offset;
  @Expose
  private Currently currently;
  @Expose
  private Hourly hourly;
  @Expose
  private Daily daily;
  @Expose
  private Flags flags;

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Currently getCurrently() {
    return currently;
  }

  public void setCurrently(Currently currently) {
    this.currently = currently;
  }

  public Hourly getHourly() {
    return hourly;
  }

  public void setHourly(Hourly hourly) {
    this.hourly = hourly;
  }

  public Daily getDaily() {
    return daily;
  }

  public void setDaily(Daily daily) {
    this.daily = daily;
  }

  public Flags getFlags() {
    return flags;
  }

  public void setFlags(Flags flags) {
    this.flags = flags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Forecast)) {
      return false;
    }

    Forecast forecast = (Forecast) o;

    if (currently != null ? !currently.equals(forecast.currently) : forecast.currently != null) {
      return false;
    }
    if (daily != null ? !daily.equals(forecast.daily) : forecast.daily != null) {
      return false;
    }
    if (flags != null ? !flags.equals(forecast.flags) : forecast.flags != null) {
      return false;
    }
    if (hourly != null ? !hourly.equals(forecast.hourly) : forecast.hourly != null) {
      return false;
    }
    if (latitude != null ? !latitude.equals(forecast.latitude) : forecast.latitude != null) {
      return false;
    }
    if (longitude != null ? !longitude.equals(forecast.longitude) : forecast.longitude != null) {
      return false;
    }
    if (offset != null ? !offset.equals(forecast.offset) : forecast.offset != null) {
      return false;
    }
    if (timezone != null ? !timezone.equals(forecast.timezone) : forecast.timezone != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = latitude != null ? latitude.hashCode() : 0;
    result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
    result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
    result = 31 * result + (offset != null ? offset.hashCode() : 0);
    result = 31 * result + (currently != null ? currently.hashCode() : 0);
    result = 31 * result + (hourly != null ? hourly.hashCode() : 0);
    result = 31 * result + (daily != null ? daily.hashCode() : 0);
    result = 31 * result + (flags != null ? flags.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Forecast{" +
           "latitude=" + latitude +
           ", longitude=" + longitude +
           ", timezone='" + timezone + '\'' +
           ", offset=" + offset +
           ", currently=" + currently +
           ", hourly=" + hourly +
           ", daily=" + daily +
           ", flags=" + flags +
           '}';
  }
}
