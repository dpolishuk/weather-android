
package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

import android.os.Parcel;
import android.os.Parcelable;

public class Forecast implements Parcelable {

  @Expose
  private Data data;

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
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

    if (data != null ? !data.equals(forecast.data) : forecast.data != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return data != null ? data.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Forecast{" +
           "data=" + data +
           '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.data, flags);
  }

  public Forecast() {
  }

  private Forecast(Parcel in) {
    this.data = in.readParcelable(Data.class.getClassLoader());
  }

  public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() {
    public Forecast createFromParcel(Parcel source) {
      return new Forecast(source);
    }

    public Forecast[] newArray(int size) {
      return new Forecast[size];
    }
  };
}
