
package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Data implements Parcelable {

  @SerializedName("current_condition")
  @Expose
  private List<CurrentCondition> currentCondition = new ArrayList<CurrentCondition>();
  @Expose
  private List<Request> request = new ArrayList<Request>();
  @Expose
  private List<Weather> weather = new ArrayList<Weather>();

  public List<CurrentCondition> getCurrentCondition() {
    return currentCondition;
  }

  public void setCurrentCondition(List<CurrentCondition> currentCondition) {
    this.currentCondition = currentCondition;
  }

  public List<Request> getRequest() {
    return request;
  }

  public void setRequest(List<Request> request) {
    this.request = request;
  }

  public List<Weather> getWeather() {
    return weather;
  }

  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Data)) {
      return false;
    }

    Data data = (Data) o;

    if (currentCondition != null ? !currentCondition.equals(data.currentCondition)
                                 : data.currentCondition != null) {
      return false;
    }
    if (request != null ? !request.equals(data.request) : data.request != null) {
      return false;
    }
    if (weather != null ? !weather.equals(data.weather) : data.weather != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = currentCondition != null ? currentCondition.hashCode() : 0;
    result = 31 * result + (request != null ? request.hashCode() : 0);
    result = 31 * result + (weather != null ? weather.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Data{" +
           "currentCondition=" + currentCondition +
           ", request=" + request +
           ", weather=" + weather +
           '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeList(this.currentCondition);
    dest.writeList(this.request);
    dest.writeList(this.weather);
  }

  public Data() {
  }

  private Data(Parcel in) {
    this.currentCondition = new ArrayList<CurrentCondition>();
    in.readList(this.currentCondition, ArrayList.class.getClassLoader());
    this.request = new ArrayList<Request>();
    in.readList(this.request, ArrayList.class.getClassLoader());
    this.weather = new ArrayList<Weather>();
    in.readList(this.weather, ArrayList.class.getClassLoader());
  }

  public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
    public Data createFromParcel(Parcel source) {
      return new Data(source);
    }

    public Data[] newArray(int size) {
      return new Data[size];
    }
  };
}
