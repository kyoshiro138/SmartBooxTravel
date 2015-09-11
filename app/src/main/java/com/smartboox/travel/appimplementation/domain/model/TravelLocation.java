package com.smartboox.travel.appimplementation.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "location")
public class TravelLocation extends Model {
    @Column(name = "name")
    private String mName;

    public String getName() {
        return mName;
    }

    public TravelLocation() {
        super();
    }

    public TravelLocation(String name) {
        mName = name;
    }
}
