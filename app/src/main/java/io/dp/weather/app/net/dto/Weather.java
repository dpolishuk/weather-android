
package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import io.dp.weather.app.WeatherIconUrl;

public class Weather implements Parcelable {

  @Expose
  private String date;
  @Expose
  private String precipMM;
  @Expose
  private String tempMaxC;
  @Expose
  private String tempMaxF;
  @Expose
  private String tempMinC;
  @Expose
  private String tempMinF;
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
  private String winddirection;
  @Expose
  private String windspeedKmph;
  @Expose
  private String windspeedMiles;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getPrecipMM() {
    return precipMM;
  }

  public void setPrecipMM(String precipMM) {
    this.precipMM = precipMM;
  }

  public String getTempMaxC() {
    return tempMaxC;
  }

  public void setTempMaxC(String tempMaxC) {
    this.tempMaxC = tempMaxC;
  }

  public String getTempMaxF() {
    return tempMaxF;
  }

  public void setTempMaxF(String tempMaxF) {
    this.tempMaxF = tempMaxF;
  }

  public String getTempMinC() {
    return tempMinC;
  }

  public void setTempMinC(String tempMinC) {
    this.tempMinC = tempMinC;
  }

  public String getTempMinF() {
    return tempMinF;
  }

  public void setTempMinF(String tempMinF) {
    this.tempMinF = tempMinF;
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

  public String getWinddirection() {
    return winddirection;
  }

  public void setWinddirection(String winddirection) {
    this.winddirection = winddirection;
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
    if (!(o instanceof Weather)) {
      return false;
    }

    Weather weather = (Weather) o;

    if (date != null ? !date.equals(weather.date) : weather.date != null) {
      return false;
    }
    if (precipMM != null ? !precipMM.equals(weather.precipMM) : weather.precipMM != null) {
      return false;
    }
    if (tempMaxC != null ? !tempMaxC.equals(weather.tempMaxC) : weather.tempMaxC != null) {
      return false;
    }
    if (tempMaxF != null ? !tempMaxF.equals(weather.tempMaxF) : weather.tempMaxF != null) {
      return false;
    }
    if (tempMinC != null ? !tempMinC.equals(weather.tempMinC) : weather.tempMinC != null) {
      return false;
    }
    if (tempMinF != null ? !tempMinF.equals(weather.tempMinF) : weather.tempMinF != null) {
      return false;
    }
    if (weatherCode != null ? !weatherCode.equals(weather.weatherCode)
                            : weather.weatherCode != null) {
      return false;
    }
    if (weatherDesc != null ? !weatherDesc.equals(weather.weatherDesc)
                            : weather.weatherDesc != null) {
      return false;
    }
    if (weatherIconUrl != null ? !weatherIconUrl.equals(weather.weatherIconUrl)
                               : weather.weatherIconUrl != null) {
      return false;
    }
    if (winddir16Point != null ? !winddir16Point.equals(weather.winddir16Point)
                               : weather.winddir16Point != null) {
      return false;
    }
    if (winddirDegree != null ? !winddirDegree.equals(weather.winddirDegree)
                              : weather.winddirDegree != null) {
      return false;
    }
    if (winddirection != null ? !winddirection.equals(weather.winddirection)
                              : weather.winddirection != null) {
      return false;
    }
    if (windspeedKmph != null ? !windspeedKmph.equals(weather.windspeedKmph)
                              : weather.windspeedKmph != null) {
      return false;
    }
    if (windspeedMiles != null ? !windspeedMiles.equals(weather.windspeedMiles)
                               : weather.windspeedMiles != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = date != null ? date.hashCode() : 0;
    result = 31 * result + (precipMM != null ? precipMM.hashCode() : 0);
    result = 31 * result + (tempMaxC != null ? tempMaxC.hashCode() : 0);
    result = 31 * result + (tempMaxF != null ? tempMaxF.hashCode() : 0);
    result = 31 * result + (tempMinC != null ? tempMinC.hashCode() : 0);
    result = 31 * result + (tempMinF != null ? tempMinF.hashCode() : 0);
    result = 31 * result + (weatherCode != null ? weatherCode.hashCode() : 0);
    result = 31 * result + (weatherDesc != null ? weatherDesc.hashCode() : 0);
    result = 31 * result + (weatherIconUrl != null ? weatherIconUrl.hashCode() : 0);
    result = 31 * result + (winddir16Point != null ? winddir16Point.hashCode() : 0);
    result = 31 * result + (winddirDegree != null ? winddirDegree.hashCode() : 0);
    result = 31 * result + (winddirection != null ? winddirection.hashCode() : 0);
    result = 31 * result + (windspeedKmph != null ? windspeedKmph.hashCode() : 0);
    result = 31 * result + (windspeedMiles != null ? windspeedMiles.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Weather{" +
           "date='" + date + '\'' +
           ", precipMM='" + precipMM + '\'' +
           ", tempMaxC='" + tempMaxC + '\'' +
           ", tempMaxF='" + tempMaxF + '\'' +
           ", tempMinC='" + tempMinC + '\'' +
           ", tempMinF='" + tempMinF + '\'' +
           ", weatherCode='" + weatherCode + '\'' +
           ", weatherDesc=" + weatherDesc +
           ", weatherIconUrl=" + weatherIconUrl +
           ", winddir16Point='" + winddir16Point + '\'' +
           ", winddirDegree='" + winddirDegree + '\'' +
           ", winddirection='" + winddirection + '\'' +
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
    dest.writeString(this.date);
    dest.writeString(this.precipMM);
    dest.writeString(this.tempMaxC);
    dest.writeString(this.tempMaxF);
    dest.writeString(this.tempMinC);
    dest.writeString(this.tempMinF);
    dest.writeString(this.weatherCode);
    dest.writeTypedList(weatherDesc);
    dest.writeTypedList(weatherIconUrl);
    dest.writeString(this.winddir16Point);
    dest.writeString(this.winddirDegree);
    dest.writeString(this.winddirection);
    dest.writeString(this.windspeedKmph);
    dest.writeString(this.windspeedMiles);
  }

  public Weather() {
  }

  private Weather(Parcel in) {
    this.date = in.readString();
    this.precipMM = in.readString();
    this.tempMaxC = in.readString();
    this.tempMaxF = in.readString();
    this.tempMinC = in.readString();
    this.tempMinF = in.readString();
    this.weatherCode = in.readString();
    in.readTypedList(weatherDesc, WeatherDesc.CREATOR);
    in.readTypedList(weatherIconUrl, WeatherIconUrl.CREATOR);
    this.winddir16Point = in.readString();
    this.winddirDegree = in.readString();
    this.winddirection = in.readString();
    this.windspeedKmph = in.readString();
    this.windspeedMiles = in.readString();
  }

  public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
    public Weather createFromParcel(Parcel source) {
      return new Weather(source);
    }

    public Weather[] newArray(int size) {
      return new Weather[size];
    }
  };
}
