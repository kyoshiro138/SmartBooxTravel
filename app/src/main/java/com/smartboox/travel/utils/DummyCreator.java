package com.smartboox.travel.utils;

import com.smartboox.travel.appimplementation.domain.model.TravelLocation;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyCreator {
    public static List<TravelPlace> createTravelPlaceList() {
        List<TravelPlace> resultList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Random rand = new Random(System.currentTimeMillis());
            int locationSize = rand.nextInt() % 10;
            List<TravelLocation> locations = new ArrayList<>();
            for (int j = 0; j < locationSize; j++) {
                locations.add(new TravelLocation(String.format("Location %d", j + 1)));
            }
            TravelPlace place = new TravelPlace(String.format("Place %d", i + 1), locations);
            resultList.add(place);
        }

        return resultList;
    }
}
