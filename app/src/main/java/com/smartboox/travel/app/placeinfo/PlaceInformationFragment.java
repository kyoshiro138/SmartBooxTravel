package com.smartboox.travel.app.placeinfo;

import android.view.View;

import com.smartboox.travel.R;
import com.smartboox.travel.app.locationlist.LocationListFragment;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.fragment.AppParamFragment;

public class PlaceInformationFragment extends AppParamFragment<TravelPlace> implements View.OnClickListener {
    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_place_info;
    }

    @Override
    protected void bindView(View rootView) {
        rootView.findViewById(R.id.btn_view_place_map).setOnClickListener(this);
        rootView.findViewById(R.id.btn_view_location_list).setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void receiveParam(TravelPlace travelPlace) {
        mActivity.getToolbar().reloadToolbar(travelPlace.getName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_view_location_list:
                getNavigator().navigateTo(new LocationListFragment(),getParam());
                break;
            case R.id.btn_view_place_map:
                break;
            default: break;
        }
    }
}
