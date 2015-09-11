package com.smartboox.travel.app.locationlist;

import android.view.View;
import android.widget.ListView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.appimplementation.fragment.AppParamFragment;

public class LocationListFragment extends AppParamFragment<TravelPlace> {
    private ListView mLocationListView;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_location_list;
    }

    @Override
    protected void bindView(View rootView) {
        mLocationListView = (ListView) rootView.findViewById(R.id.list_location);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void receiveParam(TravelPlace travelPlace) {
        mActivity.getToolbar().reloadToolbar(travelPlace.getName());

        LocationListAdapter adapter = new LocationListAdapter(getActivity(), travelPlace.getLocations());
        mLocationListView.setAdapter(adapter);
    }
}
