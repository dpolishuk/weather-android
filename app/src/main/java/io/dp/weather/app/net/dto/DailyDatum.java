package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

/**
 * Created by dp on 08/10/14.
 */
public class DailyDatum {

  @Expose
  private Integer time;
  @Expose
  private String summary;
  @Expose
  private String icon;
  @Expose
  private Integer sunriseTime;
  @Expose
  private Integer sunsetTime;
  @Expose
  private Double moonPhase;
  @Expose
  private Double precipIntensity;
  @Expose
  private Double precipIntensityMax;
  @Expose
  private Double precipProbability;
  @Expose
  private Double temperatureMin;
  @Expose
  private Integer temperatureMinTime;
  @Expose
  private Double temperatureMax;
  @Expose
  private Integer temperatureMaxTime;
  @Expose
  private Double apparentTemperatureMin;
  @Expose
  private Integer apparentTemperatureMinTime;
  @Expose
  private Double apparentTemperatureMax;
  @Expose
  private Integer apparentTemperatureMaxTime;
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
  private Integer precipIntensityMaxTime;
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

  public Integer getSunriseTime() {
    return sunriseTime;
  }

  public void setSunriseTime(Integer sunriseTime) {
    this.sunriseTime = sunriseTime;
  }

  public Integer getSunsetTime() {
    return sunsetTime;
  }

  public void setSunsetTime(Integer sunsetTime) {
    this.sunsetTime = sunsetTime;
  }

  public Double getMoonPhase() {
    return moonPhase;
  }

  public void setMoonPhase(Double moonPhase) {
    this.moonPhase = moonPhase;
  }

  public Double getPrecipIntensity() {
    return precipIntensity;
  }

  public void setPrecipIntensity(Double precipIntensity) {
    this.precipIntensity = precipIntensity;
  }

  public Double getPrecipIntensityMax() {
    return precipIntensityMax;
  }

  public void setPrecipIntensityMax(Double precipIntensityMax) {
    this.precipIntensityMax = precipIntensityMax;
  }

  public Double getPrecipProbability() {
    return precipProbability;
  }

  public void setPrecipProbability(Double precipProbability) {
    this.precipProbability = precipProbability;
  }

  public Double getTemperatureMin() {
    return temperatureMin;
  }

  public void setTemperatureMin(Double temperatureMin) {
    this.temperatureMin = temperatureMin;
  }

  public Integer getTemperatureMinTime() {
    return temperatureMinTime;
  }

  public void setTemperatureMinTime(Integer temperatureMinTime) {
    this.temperatureMinTime = temperatureMinTime;
  }

  public Double getTemperatureMax() {
    return temperatureMax;
  }

  public void setTemperatureMax(Double temperatureMax) {
    this.temperatureMax = temperatureMax;
  }

  public Integer getTemperatureMaxTime() {
    return temperatureMaxTime;
  }

  public void setTemperatureMaxTime(Integer temperatureMaxTime) {
    this.temperatureMaxTime = temperatureMaxTime;
  }

  public Double getApparentTemperatureMin() {
    return apparentTemperatureMin;
  }

  public void setApparentTemperatureMin(Double apparentTemperatureMin) {
    this.apparentTemperatureMin = apparentTemperatureMin;
  }

  public Integer getApparentTemperatureMinTime() {
    return apparentTemperatureMinTime;
  }

  public void setApparentTemperatureMinTime(Integer apparentTemperatureMinTime) {
    this.apparentTemperatureMinTime = apparentTemperatureMinTime;
  }

  public Double getApparentTemperatureMax() {
    return apparentTemperatureMax;
  }

  public void setApparentTemperatureMax(Double apparentTemperatureMax) {
    this.apparentTemperatureMax = apparentTemperatureMax;
  }

  public Integer getApparentTemperatureMaxTime() {
    return apparentTemperatureMaxTime;
  }

  public void setApparentTemperatureMaxTime(Integer apparentTemperatureMaxTime) {
    this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
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

  public Integer getPrecipIntensityMaxTime() {
    return precipIntensityMaxTime;
  }

  public void setPrecipIntensityMaxTime(Integer precipIntensityMaxTime) {
    this.precipIntensityMaxTime = precipIntensityMaxTime;
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
    if (!(o instanceof DailyDatum)) {
      return false;
    }

    DailyDatum that = (DailyDatum) o;

    if (apparentTemperatureMax != null ? !apparentTemperatureMax.equals(that.apparentTemperatureMax)
                                       : that.apparentTemperatureMax != null) {
      return false;
    }
    if (apparentTemperatureMaxTime != null ? !apparentTemperatureMaxTime
        .equals(that.apparentTemperatureMaxTime) : that.apparentTemperatureMaxTime != null) {
      return false;
    }
    if (apparentTemperatureMin != null ? !apparentTemperatureMin.equals(that.apparentTemperatureMin)
                                       : that.apparentTemperatureMin != null) {
      return false;
    }
    if (apparentTemperatureMinTime != null ? !apparentTemperatureMinTime
        .equals(that.apparentTemperatureMinTime) : that.apparentTemperatureMinTime != null) {
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
    if (moonPhase != null ? !moonPhase.equals(that.moonPhase) : that.moonPhase != null) {
      return false;
    }
    if (ozone != null ? !ozone.equals(that.ozone) : that.ozone != null) {
      return false;
    }
    if (precipIntensity != null ? !precipIntensity.equals(that.precipIntensity)
                                : that.precipIntensity != null) {
      return false;
    }
    if (precipIntensityMax != null ? !precipIntensityMax.equals(that.precipIntensityMax)
                                   : that.precipIntensityMax != null) {
      return false;
    }
    if (precipIntensityMaxTime != null ? !precipIntensityMaxTime.equals(that.precipIntensityMaxTime)
                                       : that.precipIntensityMaxTime != null) {
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
    if (sunriseTime != null ? !sunriseTime.equals(that.sunriseTime) : that.sunriseTime != null) {
      return false;
    }
    if (sunsetTime != null ? !sunsetTime.equals(that.sunsetTime) : that.sunsetTime != null) {
      return false;
    }
    if (temperatureMax != null ? !temperatureMax.equals(that.temperatureMax)
                               : that.temperatureMax != null) {
      return false;
    }
    if (temperatureMaxTime != null ? !temperatureMaxTime.equals(that.temperatureMaxTime)
                                   : that.temperatureMaxTime != null) {
      return false;
    }
    if (temperatureMin != null ? !temperatureMin.equals(that.temperatureMin)
                               : that.temperatureMin != null) {
      return false;
    }
    if (temperatureMinTime != null ? !temperatureMinTime.equals(that.temperatureMinTime)
                                   : that.temperatureMinTime != null) {
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
    result = 31 * result + (sunriseTime != null ? sunriseTime.hashCode() : 0);
    result = 31 * result + (sunsetTime != null ? sunsetTime.hashCode() : 0);
    result = 31 * result + (moonPhase != null ? moonPhase.hashCode() : 0);
    result = 31 * result + (precipIntensity != null ? precipIntensity.hashCode() : 0);
    result = 31 * result + (precipIntensityMax != null ? precipIntensityMax.hashCode() : 0);
    result = 31 * result + (precipProbability != null ? precipProbability.hashCode() : 0);
    result = 31 * result + (temperatureMin != null ? temperatureMin.hashCode() : 0);
    result = 31 * result + (temperatureMinTime != null ? temperatureMinTime.hashCode() : 0);
    result = 31 * result + (temperatureMax != null ? temperatureMax.hashCode() : 0);
    result = 31 * result + (temperatureMaxTime != null ? temperatureMaxTime.hashCode() : 0);
    result = 31 * result + (apparentTemperatureMin != null ? apparentTemperatureMin.hashCode() : 0);
    result =
        31 * result + (apparentTemperatureMinTime != null ? apparentTemperatureMinTime.hashCode()
                                                          : 0);
    result = 31 * result + (apparentTemperatureMax != null ? apparentTemperatureMax.hashCode() : 0);
    result =
        31 * result + (apparentTemperatureMaxTime != null ? apparentTemperatureMaxTime.hashCode()
                                                          : 0);
    result = 31 * result + (dewPoint != null ? dewPoint.hashCode() : 0);
    result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
    result = 31 * result + (windSpeed != null ? windSpeed.hashCode() : 0);
    result = 31 * result + (windBearing != null ? windBearing.hashCode() : 0);
    result = 31 * result + (cloudCover != null ? cloudCover.hashCode() : 0);
    result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
    result = 31 * result + (ozone != null ? ozone.hashCode() : 0);
    result = 31 * result + (precipIntensityMaxTime != null ? precipIntensityMaxTime.hashCode() : 0);
    result = 31 * result + (precipType != null ? precipType.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DailyDatum{" +
           "time=" + time +
           ", summary='" + summary + '\'' +
           ", icon='" + icon + '\'' +
           ", sunriseTime=" + sunriseTime +
           ", sunsetTime=" + sunsetTime +
           ", moonPhase=" + moonPhase +
           ", precipIntensity=" + precipIntensity +
           ", precipIntensityMax=" + precipIntensityMax +
           ", precipProbability=" + precipProbability +
           ", temperatureMin=" + temperatureMin +
           ", temperatureMinTime=" + temperatureMinTime +
           ", temperatureMax=" + temperatureMax +
           ", temperatureMaxTime=" + temperatureMaxTime +
           ", apparentTemperatureMin=" + apparentTemperatureMin +
           ", apparentTemperatureMinTime=" + apparentTemperatureMinTime +
           ", apparentTemperatureMax=" + apparentTemperatureMax +
           ", apparentTemperatureMaxTime=" + apparentTemperatureMaxTime +
           ", dewPoint=" + dewPoint +
           ", humidity=" + humidity +
           ", windSpeed=" + windSpeed +
           ", windBearing=" + windBearing +
           ", cloudCover=" + cloudCover +
           ", pressure=" + pressure +
           ", ozone=" + ozone +
           ", precipIntensityMaxTime=" + precipIntensityMaxTime +
           ", precipType='" + precipType + '\'' +
           '}';
  }
}
