package com.smartboox.travel.app.locationmap;

import android.os.Bundle;
import android.view.View;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;

public class LocationMapFragment extends AppFragment {
    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_location_map;
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
}
