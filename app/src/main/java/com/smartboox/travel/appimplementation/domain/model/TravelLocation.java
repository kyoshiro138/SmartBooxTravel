package com.smartboox.travel.appimplementation.domain.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;

@Table(name = "location")
public class TravelLocation extends Model implements Parcelable {
    @Column(name = "place_id")
    private long mPlaceId;

    @Column(name = "name")
    private String mName;

    public String getName() {
        return mName;
    }

    public long getPlaceId() {
        return mPlaceId;
    }

    public void setPlaceId(long placeId) {
        mPlaceId = placeId;
    }

    public TravelLocation() {
        super();
    }

    public TravelLocation(String name) {
        mName = name;
    }

    public TravelLocation(String name, int place) {
        mName = name;
        mPlaceId = place;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putLong("location_id", getId());

        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<TravelLocation> CREATOR = new Creator<TravelLocation>() {
        @Override
        public TravelLocation createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();
            long id = bundle.getLong("location_id");

            return ActiveAndroidDatabaseHelper.getItem(TravelLocation.class, id);
        }

        @Override
        public TravelLocation[] newArray(int size) {
            return new TravelLocation[size];
        }
    };
}
