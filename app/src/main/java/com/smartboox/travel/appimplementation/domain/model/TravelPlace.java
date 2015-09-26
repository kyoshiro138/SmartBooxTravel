package com.smartboox.travel.appimplementation.domain.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

@Table(name = "place")
public class TravelPlace extends Model implements Parcelable {
    public static final String KEY_PLACE_ID = "placeId";
    private static final String KEY_PLACE_NAME = "name";
    private static final String KEY_PLACE_LATITUDE = "latitude";
    private static final String KEY_PLACE_LONGITUDE = "longitude";
    private static final String KEY_PLACE_IMAGE_URL = "imageUrl";
    private static final String KEY_STATIC_MAP_URL = "staticMapUrl";
    private static final String KEY_LOCATION_LIST = "locationList";

    @Column(name = KEY_PLACE_ID)
    @JsonProperty(KEY_PLACE_ID)
    private int mPlaceId;
    @Column(name = KEY_PLACE_NAME)
    @JsonProperty(KEY_PLACE_NAME)
    private String mName;
    @Column(name = KEY_PLACE_LATITUDE)
    @JsonProperty(KEY_PLACE_LATITUDE)
    private float mLatitude;
    @Column(name = KEY_PLACE_LONGITUDE)
    @JsonProperty(KEY_PLACE_LONGITUDE)
    private float mLongitude;
    @Column(name = KEY_PLACE_IMAGE_URL)
    @JsonProperty(KEY_PLACE_IMAGE_URL)
    private String mImageUrl;
    @Column(name = KEY_STATIC_MAP_URL)
    @JsonProperty(KEY_STATIC_MAP_URL)
    private String mStaticMapUrl;
    @JsonProperty(KEY_LOCATION_LIST)
    private List<TravelLocation> mLocations;

    public TravelPlace() {
        super();
    }

    public TravelPlace(String name, List<TravelLocation> locations) {
        mName = name;
        mLocations = locations;
    }

    public int getPlaceId() { return mPlaceId; }

    public String getName() {
        return mName;
    }

    public float getLatitude() { return mLatitude; }

    public float getLongitude() { return mLongitude; }

    public String getImageUrl() { return mImageUrl; }

    public String getStaticMapUrl() { return mStaticMapUrl; }

    public List<TravelLocation> getLocations() {
        if (mLocations == null) {
            mLocations = new ArrayList<>();
        }
        return mLocations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PLACE_ID, getPlaceId());
        bundle.putParcelableArrayList(KEY_LOCATION_LIST, new ArrayList<>(mLocations));

        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<TravelPlace> CREATOR = new Creator<TravelPlace>() {
        @Override
        public TravelPlace createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();
            int id = bundle.getInt(KEY_PLACE_ID);
            List<TravelLocation> locationList = bundle.getParcelableArrayList(KEY_LOCATION_LIST);

            TravelPlace place = ActiveAndroidDatabaseHelper.getItemById(TravelPlace.class, KEY_PLACE_ID, id);
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
