package com.smartboox.travel.appimplementation.manager;

import android.content.Context;

import com.activeandroid.query.Select;
import com.smartboox.travel.appimplementation.domain.model.TravelLocation;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.appimplementation.service.AppResponseObject;
import com.smartboox.travel.appimplementation.service.AppRestClient;
import com.smartboox.travel.appimplementation.service.ServiceConstant;
import com.smartboox.travel.appimplementation.service.response.GetTravelDataResponseObject;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;
import com.smartboox.travel.core.database.OnTransactionExecuteListener;
import com.smartboox.travel.core.dialog.progress.SystemProgressDialog;
import com.smartboox.travel.core.service.client.OnServiceResponseListener;
import com.smartboox.travel.utils.ApplicationPreference;

import java.util.ArrayList;
import java.util.List;

public class TravelManager {
    private Context mContext;
    private ApplicationPreference mPreference;

    public static final String SERVICE_GET_TRAVEL_DATA = "SERVICE_GET_TRAVEL_DATA";

    public TravelManager(Context context) {
        mContext = context;
        mPreference = new ApplicationPreference(context);
    }

    public List<TravelPlace> getLocalTravelPlaceList() {
        List<TravelPlace> placeList = new ArrayList<>();
        placeList = ActiveAndroidDatabaseHelper.getList(TravelPlace.class);
        for (TravelPlace place : placeList) {
            List<TravelLocation> locationList = ActiveAndroidDatabaseHelper.getListById(TravelLocation.class, TravelPlace.KEY_PLACE_ID, place.getPlaceId());
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
                    place.save();
                    ActiveAndroidDatabaseHelper.saveAll(place.getLocations());
                }
            }
        });
    }

    public void requestTravelData(long timestamp, OnServiceResponseListener<AppResponseObject> listener) {
        String key = (String) mPreference.getValue(ApplicationPreference.Key.PREFERENCE_AUTH_KEY, "", ApplicationPreference.PREFERENCE_TYPE_STRING);
        String url = String.format("%s?key=%s&timestamp=%d", ServiceConstant.URL_GET_TRAVEL_DATA,key,timestamp);

        SystemProgressDialog<String> dialog = new SystemProgressDialog<>(mContext);
        dialog.setMessage("Getting travel data...");

        AppRestClient<GetTravelDataResponseObject> restClient = new AppRestClient<>(mContext, SERVICE_GET_TRAVEL_DATA, url, GetTravelDataResponseObject.class, listener);
        restClient.setProgressDialog(dialog);
        restClient.executeGet();
    }
}
