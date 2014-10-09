package io.dp.weather.app.db.table;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by dp on 09/10/14.
 */

@DatabaseTable(tableName = "cities")
public class City {

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

  public City() {
  }

  public City(String name, double lat, double lon) {
    this.name = name;
    this.lat = lat;
    this.lon = lon;
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
}
