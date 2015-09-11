package com.smartboox.travel.appimplementation.manager;

import com.activeandroid.query.Select;
import com.smartboox.travel.appimplementation.domain.model.TravelLocation;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;
import com.smartboox.travel.core.database.OnTransactionExecuteListener;

import java.util.ArrayList;
import java.util.List;

public class PlaceManager {

    public PlaceManager() {

    }

    public List<TravelPlace> getTravelPlaceList() {
        // TODO: Get place list from service
        List<TravelPlace> placeList = new ArrayList<>();
        placeList = ActiveAndroidDatabaseHelper.getList(TravelPlace.class);
        for (TravelPlace place : placeList) {
            List<TravelLocation> locationList = new Select().from(TravelLocation.class).where("place_id = ?", place.getId()).execute();
            if (locationList != null && locationList.size() > 0) {
                place.getLocations().clear();
                place.getLocations().addAll(locationList);
            }
        }

        return placeList;
    }

    public void saveTravelPlaceList(final List<TravelPlace> placeList) {
        ActiveAndroidDatabaseHelper.startTransactionExecution(new OnTransactionExecuteListener() {
            @Override
            public void onExecute() {
                ActiveAndroidDatabaseHelper.removeAll(TravelPlace.class);
                ActiveAndroidDatabaseHelper.removeAll(TravelLocation.class);

                for (TravelPlace place : placeList) {
                    long placeId = place.save();
                    for (TravelLocation location : place.getLocations()) {
                        location.setPlaceId(placeId);
                    }
                    ActiveAndroidDatabaseHelper.saveAll(place.getLocations());
                }
            }
        });
    }
}
