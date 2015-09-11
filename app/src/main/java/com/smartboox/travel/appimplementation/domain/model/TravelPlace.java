package com.smartboox.travel.appimplementation.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "place")
public class TravelPlace extends Model {
    @Column(name = "name")
    private String mName;

    public String getName() {
        return mName;
    }

    private List<TravelLocation> mLocations;

    public List<TravelLocation> getLocations() {
        return mLocations;
    }

    public TravelPlace() {
        super();
    }

    public TravelPlace(String name, List<TravelLocation> locations) {
        mName = name;
        mLocations = locations;
    }
}
