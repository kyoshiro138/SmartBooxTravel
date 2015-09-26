package com.smartboox.travel.appimplementation.domain.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;

@Table(name = "location")
public class TravelLocation extends Model implements Parcelable {
    private static final String KEY_LOCATION_ID = "locationId";
    private static final String KEY_PLACE_ID = "placeId";
    private static final String KEY_LOCATION_NAME = "name";
    private static final String KEY_LOCATION_DESCRIPTION = "description";

    @Column(name = KEY_LOCATION_ID)
    @JsonProperty(KEY_LOCATION_ID)
    private int mLocationId;
    @Column(name = KEY_PLACE_ID)
    @JsonProperty(KEY_PLACE_ID)
    private int mPlaceId;
    @Column(name = KEY_LOCATION_NAME)
    @JsonProperty(KEY_LOCATION_NAME)
    private String mName;
    @Column(name = KEY_LOCATION_DESCRIPTION)
    @JsonProperty(KEY_LOCATION_DESCRIPTION)
    private String mDescription;

    public int getLocationId() { return mLocationId; }
    public int getPlaceId() {
        return mPlaceId;
    }
    public String getName() {
        return mName;
    }
    public String getDescription() {
        return mDescription;
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
        bundle.putInt(KEY_LOCATION_ID, getLocationId());

        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<TravelLocation> CREATOR = new Creator<TravelLocation>() {
        @Override
        public TravelLocation createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();
            int id = bundle.getInt(KEY_LOCATION_ID);

            return ActiveAndroidDatabaseHelper.getItemById(TravelLocation.class, KEY_LOCATION_ID, id);
        }

        @Override
        public TravelLocation[] newArray(int size) {
            return new TravelLocation[size];
        }
    };
}
