package com.smartboox.travel.appimplementation.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "location")
public class TravelLocation extends Model {
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
}
