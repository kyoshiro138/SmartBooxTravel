package com.smartboox.travel.appimplementation.domain.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

@Table(name = "place")
public class TravelPlace extends Model implements Parcelable {
    @Column(name = "name")
    private String mName;

    public String getName() {
        return mName;
    }

    private List<TravelLocation> mLocations;

    public List<TravelLocation> getLocations() {
        if (mLocations == null) {
            mLocations = new ArrayList<>();
        }
        return mLocations;
    }

    public TravelPlace() {
        super();
    }

    public TravelPlace(String name, List<TravelLocation> locations) {
        mName = name;
        mLocations = locations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putLong("place_id", getId());
        bundle.putParcelableArrayList("location_list", new ArrayList<>(mLocations));

        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<TravelPlace> CREATOR = new Creator<TravelPlace>() {
        @Override
        public TravelPlace createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();
            long id = bundle.getLong("place_id");
            List<TravelLocation> locationList = bundle.getParcelableArrayList("location_list");

            TravelPlace place = ActiveAndroidDatabaseHelper.getItem(TravelPlace.class, id);
            place.getLocations().clear();
            if (locationList != null) {
                place.getLocations().addAll(locationList);
            }

            return place;
        }

        @Override
        public TravelPlace[] newArray(int size) {
            return new TravelPlace[size];
        }
    };
}
