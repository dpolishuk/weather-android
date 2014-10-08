
package io.dp.weather.app;

import com.google.gson.annotations.Expose;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherIconUrl implements Parcelable {

  @Expose
  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WeatherIconUrl)) {
      return false;
    }

    WeatherIconUrl that = (WeatherIconUrl) o;

    if (value != null ? !value.equals(that.value) : that.value != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "WeatherIconUrl{" +
           "value='" + value + '\'' +
           '}';
  }

  public WeatherIconUrl() {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.value);
  }

  private WeatherIconUrl(Parcel in) {
    this.value = in.readString();
  }

  public static final Creator<WeatherIconUrl> CREATOR = new Creator<WeatherIconUrl>() {
    public WeatherIconUrl createFromParcel(Parcel source) {
      return new WeatherIconUrl(source);
    }

    public WeatherIconUrl[] newArray(int size) {
      return new WeatherIconUrl[size];
    }
  };
}
