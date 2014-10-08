
package io.dp.weather.app.net.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import io.dp.weather.app.WeatherIconUrl;

public class CurrentCondition implements Parcelable {

  @Expose
  private String cloudcover;
  @Expose
  private String humidity;
  @SerializedName("observation_time")
  @Expose
  private String observationTime;
  @Expose
  private String precipMM;
  @Expose
  private String pressure;
  @SerializedName("temp_C")
  @Expose
  private String tempC;
  @SerializedName("temp_F")
  @Expose
  private String tempF;
  @Expose
  private String visibility;
  @Expose
  private String weatherCode;
  @Expose
  private List<WeatherDesc> weatherDesc = new ArrayList<WeatherDesc>();
  @Expose
  private List<WeatherIconUrl> weatherIconUrl = new ArrayList<WeatherIconUrl>();
  @Expose
  private String winddir16Point;
  @Expose
  private String winddirDegree;
  @Expose
  private String windspeedKmph;
  @Expose
  private String windspeedMiles;

  public String getCloudcover() {
    return cloudcover;
  }

  public void setCloudcover(String cloudcover) {
    this.cloudcover = cloudcover;
  }

  public String getHumidity() {
    return humidity;
  }

  public void setHumidity(String humidity) {
    this.humidity = humidity;
  }

  public String getObservationTime() {
    return observationTime;
  }

  public void setObservationTime(String observationTime) {
    this.observationTime = observationTime;
  }

  public String getPrecipMM() {
    return precipMM;
  }

  public void setPrecipMM(String precipMM) {
    this.precipMM = precipMM;
  }

  public String getPressure() {
    return pressure;
  }

  public void setPressure(String pressure) {
    this.pressure = pressure;
  }

  public String getTempC() {
    return tempC;
  }

  public void setTempC(String tempC) {
    this.tempC = tempC;
  }

  public String getTempF() {
    return tempF;
  }

  public void setTempF(String tempF) {
    this.tempF = tempF;
  }

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public String getWeatherCode() {
    return weatherCode;
  }

  public void setWeatherCode(String weatherCode) {
    this.weatherCode = weatherCode;
  }

  public List<WeatherDesc> getWeatherDesc() {
    return weatherDesc;
  }

  public void setWeatherDesc(List<WeatherDesc> weatherDesc) {
    this.weatherDesc = weatherDesc;
  }

  public List<WeatherIconUrl> getWeatherIconUrl() {
    return weatherIconUrl;
  }

  public void setWeatherIconUrl(List<WeatherIconUrl> weatherIconUrl) {
    this.weatherIconUrl = weatherIconUrl;
  }

  public String getWinddir16Point() {
    return winddir16Point;
  }

  public void setWinddir16Point(String winddir16Point) {
    this.winddir16Point = winddir16Point;
  }

  public String getWinddirDegree() {
    return winddirDegree;
  }

  public void setWinddirDegree(String winddirDegree) {
    this.winddirDegree = winddirDegree;
  }

  public String getWindspeedKmph() {
    return windspeedKmph;
  }

  public void setWindspeedKmph(String windspeedKmph) {
    this.windspeedKmph = windspeedKmph;
  }

  public String getWindspeedMiles() {
    return windspeedMiles;
  }

  public void setWindspeedMiles(String windspeedMiles) {
    this.windspeedMiles = windspeedMiles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CurrentCondition)) {
      return false;
    }

    CurrentCondition that = (CurrentCondition) o;

    if (cloudcover != null ? !cloudcover.equals(that.cloudcover) : that.cloudcover != null) {
      return false;
    }
    if (humidity != null ? !humidity.equals(that.humidity) : that.humidity != null) {
      return false;
    }
    if (observationTime != null ? !observationTime.equals(that.observationTime)
                                : that.observationTime != null) {
      return false;
    }
    if (precipMM != null ? !precipMM.equals(that.precipMM) : that.precipMM != null) {
      return false;
    }
    if (pressure != null ? !pressure.equals(that.pressure) : that.pressure != null) {
      return false;
    }
    if (tempC != null ? !tempC.equals(that.tempC) : that.tempC != null) {
      return false;
    }
    if (tempF != null ? !tempF.equals(that.tempF) : that.tempF != null) {
      return false;
    }
    if (visibility != null ? !visibility.equals(that.visibility) : that.visibility != null) {
      return false;
    }
    if (weatherCode != null ? !weatherCode.equals(that.weatherCode) : that.weatherCode != null) {
      return false;
    }
    if (weatherDesc != null ? !weatherDesc.equals(that.weatherDesc) : that.weatherDesc != null) {
      return false;
    }
    if (weatherIconUrl != null ? !weatherIconUrl.equals(that.weatherIconUrl)
                               : that.weatherIconUrl != null) {
      return false;
    }
    if (winddir16Point != null ? !winddir16Point.equals(that.winddir16Point)
                               : that.winddir16Point != null) {
      return false;
    }
    if (winddirDegree != null ? !winddirDegree.equals(that.winddirDegree)
                              : that.winddirDegree != null) {
      return false;
    }
    if (windspeedKmph != null ? !windspeedKmph.equals(that.windspeedKmph)
                              : that.windspeedKmph != null) {
      return false;
    }
    if (windspeedMiles != null ? !windspeedMiles.equals(that.windspeedMiles)
                               : that.windspeedMiles != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = cloudcover != null ? cloudcover.hashCode() : 0;
    result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
    result = 31 * result + (observationTime != null ? observationTime.hashCode() : 0);
    result = 31 * result + (precipMM != null ? precipMM.hashCode() : 0);
    result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
    result = 31 * result + (tempC != null ? tempC.hashCode() : 0);
    result = 31 * result + (tempF != null ? tempF.hashCode() : 0);
    result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
    result = 31 * result + (weatherCode != null ? weatherCode.hashCode() : 0);
    result = 31 * result + (weatherDesc != null ? weatherDesc.hashCode() : 0);
    result = 31 * result + (weatherIconUrl != null ? weatherIconUrl.hashCode() : 0);
    result = 31 * result + (winddir16Point != null ? winddir16Point.hashCode() : 0);
    result = 31 * result + (winddirDegree != null ? winddirDegree.hashCode() : 0);
    result = 31 * result + (windspeedKmph != null ? windspeedKmph.hashCode() : 0);
    result = 31 * result + (windspeedMiles != null ? windspeedMiles.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "CurrentCondition{" +
           "cloudcover='" + cloudcover + '\'' +
           ", humidity='" + humidity + '\'' +
           ", observationTime='" + observationTime + '\'' +
           ", precipMM='" + precipMM + '\'' +
           ", pressure='" + pressure + '\'' +
           ", tempC='" + tempC + '\'' +
           ", tempF='" + tempF + '\'' +
           ", visibility='" + visibility + '\'' +
           ", weatherCode='" + weatherCode + '\'' +
           ", weatherDesc=" + weatherDesc +
           ", weatherIconUrl=" + weatherIconUrl +
           ", winddir16Point='" + winddir16Point + '\'' +
           ", winddirDegree='" + winddirDegree + '\'' +
           ", windspeedKmph='" + windspeedKmph + '\'' +
           ", windspeedMiles='" + windspeedMiles + '\'' +
           '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.cloudcover);
    dest.writeString(this.humidity);
    dest.writeString(this.observationTime);
    dest.writeString(this.precipMM);
    dest.writeString(this.pressure);
    dest.writeString(this.tempC);
    dest.writeString(this.tempF);
    dest.writeString(this.visibility);
    dest.writeString(this.weatherCode);
    dest.writeList(this.weatherDesc);
    dest.writeList(this.weatherIconUrl);
    dest.writeString(this.winddir16Point);
    dest.writeString(this.winddirDegree);
    dest.writeString(this.windspeedKmph);
    dest.writeString(this.windspeedMiles);
  }

  public CurrentCondition() {
  }

  private CurrentCondition(Parcel in) {
    this.cloudcover = in.readString();
    this.humidity = in.readString();
    this.observationTime = in.readString();
    this.precipMM = in.readString();
    this.pressure = in.readString();
    this.tempC = in.readString();
    this.tempF = in.readString();
    this.visibility = in.readString();
    this.weatherCode = in.readString();
    this.weatherDesc = new ArrayList<WeatherDesc>();
    in.readList(this.weatherDesc, ArrayList.class.getClassLoader());
    this.weatherIconUrl = new ArrayList<WeatherIconUrl>();
    in.readList(this.weatherIconUrl, ArrayList.class.getClassLoader());
    this.winddir16Point = in.readString();
    this.winddirDegree = in.readString();
    this.windspeedKmph = in.readString();
    this.windspeedMiles = in.readString();
  }

  public static final Parcelable.Creator<CurrentCondition>
      CREATOR =
      new Parcelable.Creator<CurrentCondition>() {
        public CurrentCondition createFromParcel(Parcel source) {
          return new CurrentCondition(source);
        }

        public CurrentCondition[] newArray(int size) {
          return new CurrentCondition[size];
        }
      };
}
