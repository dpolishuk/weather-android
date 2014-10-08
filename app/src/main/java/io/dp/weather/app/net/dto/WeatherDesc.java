
package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherDesc implements Parcelable {

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
    if (!(o instanceof WeatherDesc)) {
      return false;
    }

    WeatherDesc that = (WeatherDesc) o;

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
    return "WeatherDesc{" +
           "value='" + value + '\'' +
           '}';
  }

  public WeatherDesc() {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.value);
  }

  private WeatherDesc(Parcel in) {
    this.value = in.readString();
  }

  public static final Creator<WeatherDesc> CREATOR = new Creator<WeatherDesc>() {
    public WeatherDesc createFromParcel(Parcel source) {
      return new WeatherDesc(source);
    }

    public WeatherDesc[] newArray(int size) {
      return new WeatherDesc[size];
    }
  };
}
