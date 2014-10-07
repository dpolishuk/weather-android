package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

public class Currently {

  @Expose
  private Integer time;
  @Expose
  private String summary;
  @Expose
  private String icon;
  @Expose
  private Integer precipIntensity;
  @Expose
  private Integer precipProbability;
  @Expose
  private Double temperature;
  @Expose
  private Double apparentTemperature;
  @Expose
  private Double dewPoint;
  @Expose
  private Double humidity;
  @Expose
  private Double windSpeed;
  @Expose
  private Integer windBearing;
  @Expose
  private Double cloudCover;
  @Expose
  private Double pressure;
  @Expose
  private Double ozone;

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Integer getPrecipIntensity() {
    return precipIntensity;
  }

  public void setPrecipIntensity(Integer precipIntensity) {
    this.precipIntensity = precipIntensity;
  }

  public Integer getPrecipProbability() {
    return precipProbability;
  }

  public void setPrecipProbability(Integer precipProbability) {
    this.precipProbability = precipProbability;
  }

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public Double getApparentTemperature() {
    return apparentTemperature;
  }

  public void setApparentTemperature(Double apparentTemperature) {
    this.apparentTemperature = apparentTemperature;
  }

  public Double getDewPoint() {
    return dewPoint;
  }

  public void setDewPoint(Double dewPoint) {
    this.dewPoint = dewPoint;
  }

  public Double getHumidity() {
    return humidity;
  }

  public void setHumidity(Double humidity) {
    this.humidity = humidity;
  }

  public Double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(Double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public Integer getWindBearing() {
    return windBearing;
  }

  public void setWindBearing(Integer windBearing) {
    this.windBearing = windBearing;
  }

  public Double getCloudCover() {
    return cloudCover;
  }

  public void setCloudCover(Double cloudCover) {
    this.cloudCover = cloudCover;
  }

  public Double getPressure() {
    return pressure;
  }

  public void setPressure(Double pressure) {
    this.pressure = pressure;
  }

  public Double getOzone() {
    return ozone;
  }

  public void setOzone(Double ozone) {
    this.ozone = ozone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Currently)) {
      return false;
    }

    Currently currently = (Currently) o;

    if (apparentTemperature != null ? !apparentTemperature.equals(currently.apparentTemperature)
                                    : currently.apparentTemperature != null) {
      return false;
    }
    if (cloudCover != null ? !cloudCover.equals(currently.cloudCover)
                           : currently.cloudCover != null) {
      return false;
    }
    if (dewPoint != null ? !dewPoint.equals(currently.dewPoint) : currently.dewPoint != null) {
      return false;
    }
    if (humidity != null ? !humidity.equals(currently.humidity) : currently.humidity != null) {
      return false;
    }
    if (icon != null ? !icon.equals(currently.icon) : currently.icon != null) {
      return false;
    }
    if (ozone != null ? !ozone.equals(currently.ozone) : currently.ozone != null) {
      return false;
    }
    if (precipIntensity != null ? !precipIntensity.equals(currently.precipIntensity)
                                : currently.precipIntensity != null) {
      return false;
    }
    if (precipProbability != null ? !precipProbability.equals(currently.precipProbability)
                                  : currently.precipProbability != null) {
      return false;
    }
    if (pressure != null ? !pressure.equals(currently.pressure) : currently.pressure != null) {
      return false;
    }
    if (summary != null ? !summary.equals(currently.summary) : currently.summary != null) {
      return false;
    }
    if (temperature != null ? !temperature.equals(currently.temperature)
                            : currently.temperature != null) {
      return false;
    }
    if (time != null ? !time.equals(currently.time) : currently.time != null) {
      return false;
    }
    if (windBearing != null ? !windBearing.equals(currently.windBearing)
                            : currently.windBearing != null) {
      return false;
    }
    if (windSpeed != null ? !windSpeed.equals(currently.windSpeed) : currently.windSpeed != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = time != null ? time.hashCode() : 0;
    result = 31 * result + (summary != null ? summary.hashCode() : 0);
    result = 31 * result + (icon != null ? icon.hashCode() : 0);
    result = 31 * result + (precipIntensity != null ? precipIntensity.hashCode() : 0);
    result = 31 * result + (precipProbability != null ? precipProbability.hashCode() : 0);
    result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
    result = 31 * result + (apparentTemperature != null ? apparentTemperature.hashCode() : 0);
    result = 31 * result + (dewPoint != null ? dewPoint.hashCode() : 0);
    result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
    result = 31 * result + (windSpeed != null ? windSpeed.hashCode() : 0);
    result = 31 * result + (windBearing != null ? windBearing.hashCode() : 0);
    result = 31 * result + (cloudCover != null ? cloudCover.hashCode() : 0);
    result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
    result = 31 * result + (ozone != null ? ozone.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Currently{" +
           "time=" + time +
           ", summary='" + summary + '\'' +
           ", icon='" + icon + '\'' +
           ", precipIntensity=" + precipIntensity +
           ", precipProbability=" + precipProbability +
           ", temperature=" + temperature +
           ", apparentTemperature=" + apparentTemperature +
           ", dewPoint=" + dewPoint +
           ", humidity=" + humidity +
           ", windSpeed=" + windSpeed +
           ", windBearing=" + windBearing +
           ", cloudCover=" + cloudCover +
           ", pressure=" + pressure +
           ", ozone=" + ozone +
           '}';
  }
}