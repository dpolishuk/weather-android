package io.dp.weather.app.db.table;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by dp on 09/10/14.
 */

@DatabaseTable(tableName = "places")
public class Place {

  public static final String ID = "id";
  public static final String NAME = "name";
  private static final String LAT = "lat";
  private static final String LON = "lon";

  @DatabaseField(generatedId = true, dataType = DataType.LONG, columnName = ID)
  private long id;

  @DatabaseField(dataType = DataType.STRING, columnName = NAME)
  private String name;

  @DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName = LAT)
  private Double lat;

  @DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName = LON)
  private Double lon;

  public Place() {
  }

  public Place(String name, double lat, double lon) {
    this.name = name;
    this.lat = lat;
    this.lon = lon;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLon() {
    return lon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Place)) {
      return false;
    }

    Place place = (Place) o;

    if (id != place.id) {
      return false;
    }
    if (lat != null ? !lat.equals(place.lat) : place.lat != null) {
      return false;
    }
    if (lon != null ? !lon.equals(place.lon) : place.lon != null) {
      return false;
    }
    if (name != null ? !name.equals(place.name) : place.name != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (lat != null ? lat.hashCode() : 0);
    result = 31 * result + (lon != null ? lon.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Place{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", lat=" + lat +
           ", lon=" + lon +
           '}';
  }
}
