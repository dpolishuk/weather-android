package io.dp.weather.app.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import io.dp.weather.app.R;
import io.dp.weather.app.db.DatabaseHelper;
import io.dp.weather.app.db.table.Place;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Created by dp on 10/10/14.
 */
public class Observables {

  public static Observable<Place> getGeoForPlace(final Context context,
                                                 final DatabaseHelper dbHelper,
                                                 final Geocoder geocoder,
                                                 final String lookupPlace) {
    return Observable.create(new Observable.OnSubscribe<List<Address>>() {
      @Override
      public void call(Subscriber<? super List<Address>> subscriber) {
        try {
          Timber.v("Before ask geocoder");
          synchronized (geocoder) {
            subscriber.onNext(geocoder.getFromLocationName(lookupPlace, 1));
          }
        } catch (IOException e) {
          Toast.makeText(context, R.string.cannot_find_geo_for_specified_location,
                         Toast.LENGTH_SHORT).show();
          Timber.e(e, "Cannot find geo for location name");
          subscriber.onError(e);
        } finally {
          subscriber.onCompleted();
        }
      }
    }).flatMap(new Func1<List<Address>, Observable<Place>>() {
      @Override
      public Observable<Place> call(final List<Address> addresses) {
        return Observable.create(new Observable.OnSubscribe<Place>() {
          @Override
          public void call(Subscriber<? super Place> subscriber) {
            Timber.v("Im here! " + addresses);
            if (addresses != null && addresses.size() > 0) {
              Address address = addresses.get(0);
              try {
                Place place = new Place(lookupPlace, address.getLatitude(), address.getLongitude());
                dbHelper.getPlaceDao().create(place);

                subscriber.onNext(place);
              } catch (SQLException e) {
                Toast.makeText(context,
                               R.string.something_went_wrong_with_adding_new_location,
                               Toast.LENGTH_SHORT).show();
                Timber.e(e, "Cannot add city");
                subscriber.onError(e);
              } finally {
                subscriber.onCompleted();
              }
            }
          }
        });
      }
    });
  }
}
