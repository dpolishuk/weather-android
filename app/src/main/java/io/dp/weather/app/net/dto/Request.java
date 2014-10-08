
package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {

  @Expose
  private String query;
  @Expose
  private String type;

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Request)) {
      return false;
    }

    Request request = (Request) o;

    if (query != null ? !query.equals(request.query) : request.query != null) {
      return false;
    }
    if (type != null ? !type.equals(request.type) : request.type != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = query != null ? query.hashCode() : 0;
    result = 31 * result + (type != null ? type.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Request{" +
           "query='" + query + '\'' +
           ", type='" + type + '\'' +
           '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.query);
    dest.writeString(this.type);
  }

  public Request() {
  }

  private Request(Parcel in) {
    this.query = in.readString();
    this.type = in.readString();
  }

  public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {
    public Request createFromParcel(Parcel source) {
      return new Request(source);
    }

    public Request[] newArray(int size) {
      return new Request[size];
    }
  };
}
