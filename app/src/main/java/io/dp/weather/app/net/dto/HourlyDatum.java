package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

/**
 * Created by dp on 08/10/14.
 */
public class HourlyDatum {

  @Expose
  private Integer time;
  @Expose
  private String summary;
  @Expose
  private String icon;
  @Expose
  private Double precipIntensity;
  @Expose
  private Double precipProbability;
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
  @Expose
  private String precipType;

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

  public Double getPrecipIntensity() {
    return precipIntensity;
  }

  public void setPrecipIntensity(Double precipIntensity) {
    this.precipIntensity = precipIntensity;
  }

  public Double getPrecipProbability() {
    return precipProbability;
  }

  public void setPrecipProbability(Double precipProbability) {
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

  public String getPrecipType() {
    return precipType;
  }

  public void setPrecipType(String precipType) {
    this.precipType = precipType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HourlyDatum)) {
      return false;
    }

    HourlyDatum that = (HourlyDatum) o;

    if (apparentTemperature != null ? !apparentTemperature.equals(that.apparentTemperature)
                                    : that.apparentTemperature != null) {
      return false;
    }
    if (cloudCover != null ? !cloudCover.equals(that.cloudCover) : that.cloudCover != null) {
      return false;
    }
    if (dewPoint != null ? !dewPoint.equals(that.dewPoint) : that.dewPoint != null) {
      return false;
    }
    if (humidity != null ? !humidity.equals(that.humidity) : that.humidity != null) {
      return false;
    }
    if (icon != null ? !icon.equals(that.icon) : that.icon != null) {
      return false;
    }
    if (ozone != null ? !ozone.equals(that.ozone) : that.ozone != null) {
      return false;
    }
    if (precipIntensity != null ? !precipIntensity.equals(that.precipIntensity)
                                : that.precipIntensity != null) {
      return false;
    }
    if (precipProbability != null ? !precipProbability.equals(that.precipProbability)
                                  : that.precipProbability != null) {
      return false;
    }
    if (precipType != null ? !precipType.equals(that.precipType) : that.precipType != null) {
      return false;
    }
    if (pressure != null ? !pressure.equals(that.pressure) : that.pressure != null) {
      return false;
    }
    if (summary != null ? !summary.equals(that.summary) : that.summary != null) {
      return false;
    }
    if (temperature != null ? !temperature.equals(that.temperature) : that.temperature != null) {
      return false;
    }
    if (time != null ? !time.equals(that.time) : that.time != null) {
      return false;
    }
    if (windBearing != null ? !windBearing.equals(that.windBearing) : that.windBearing != null) {
      return false;
    }
    if (windSpeed != null ? !windSpeed.equals(that.windSpeed) : that.windSpeed != null) {
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
    result = 31 * result + (precipType != null ? precipType.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "HourlyDatum{" +
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
           ", precipType='" + precipType + '\'' +
           '}';
  }
}