package com.smartboox.travel.app.placemap;

import android.os.Bundle;
import android.view.View;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.fragment.AppParamFragment;

public class PlaceMapFragment extends AppParamFragment<TravelPlace> {
    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_place_map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity.setDrawerEnabled(true);
    }

    @Override
    protected void bindView(View rootView) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void receiveParam(TravelPlace travelPlace) {
        mActivity.getToolbar().reloadToolbar(travelPlace.getName());
    }
}
