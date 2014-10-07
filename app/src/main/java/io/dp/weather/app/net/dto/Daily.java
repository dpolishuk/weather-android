package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Daily {

  @Expose
  private String summary;
  @Expose
  private String icon;
  @Expose
  private List<DailyDatum> data = new ArrayList<DailyDatum>();

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

  public List<DailyDatum> getData() {
    return data;
  }

  public void setData(List<DailyDatum> data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Daily)) {
      return false;
    }

    Daily daily = (Daily) o;

    if (data != null ? !data.equals(daily.data) : daily.data != null) {
      return false;
    }
    if (icon != null ? !icon.equals(daily.icon) : daily.icon != null) {
      return false;
    }
    if (summary != null ? !summary.equals(daily.summary) : daily.summary != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = summary != null ? summary.hashCode() : 0;
    result = 31 * result + (icon != null ? icon.hashCode() : 0);
    result = 31 * result + (data != null ? data.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Daily{" +
           "summary='" + summary + '\'' +
           ", icon='" + icon + '\'' +
           ", data=" + data +
           '}';
  }
}
