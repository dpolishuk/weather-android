package io.dp.weather.app.net.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Flags {

  @Expose
  private List<String> sources = new ArrayList<String>();
  @SerializedName("isd-stations")
  @Expose
  private List<String> isdStations = new ArrayList<String>();
  @SerializedName("madis-stations")
  @Expose
  private List<String> madisStations = new ArrayList<String>();
  @Expose
  private String units;

  public List<String> getSources() {
    return sources;
  }

  public void setSources(List<String> sources) {
    this.sources = sources;
  }

  public List<String> getIsdStations() {
    return isdStations;
  }

  public void setIsdStations(List<String> isdStations) {
    this.isdStations = isdStations;
  }

  public List<String> getMadisStations() {
    return madisStations;
  }

  public void setMadisStations(List<String> madisStations) {
    this.madisStations = madisStations;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Flags)) {
      return false;
    }

    Flags flags = (Flags) o;

    if (isdStations != null ? !isdStations.equals(flags.isdStations) : flags.isdStations != null) {
      return false;
    }
    if (madisStations != null ? !madisStations.equals(flags.madisStations)
                              : flags.madisStations != null) {
      return false;
    }
    if (sources != null ? !sources.equals(flags.sources) : flags.sources != null) {
      return false;
    }
    if (units != null ? !units.equals(flags.units) : flags.units != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = sources != null ? sources.hashCode() : 0;
    result = 31 * result + (isdStations != null ? isdStations.hashCode() : 0);
    result = 31 * result + (madisStations != null ? madisStations.hashCode() : 0);
    result = 31 * result + (units != null ? units.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Flags{" +
           "sources=" + sources +
           ", isdStations=" + isdStations +
           ", madisStations=" + madisStations +
           ", units='" + units + '\'' +
           '}';
  }
}