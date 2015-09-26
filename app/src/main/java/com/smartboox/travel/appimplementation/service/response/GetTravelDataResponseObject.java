package com.smartboox.travel.appimplementation.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.appimplementation.service.AppResponseObject;

import java.util.List;

public class GetTravelDataResponseObject extends AppResponseObject<GetTravelDataResponseObject.GetTravelDataResponseData> {
    public class GetTravelDataResponseData {
        @JsonProperty("placeList")
        private List<TravelPlace> mPlaceList;

        public List<TravelPlace> getPlaceList() { return mPlaceList; }
    }
}
