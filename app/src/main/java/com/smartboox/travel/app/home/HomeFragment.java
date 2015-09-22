package com.smartboox.travel.app.home;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;

import com.smartboox.travel.R;
import com.smartboox.travel.app.locationlist.LocationListFragment;
import com.smartboox.travel.app.placemap.PlaceMapFragment;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.PlaceManager;
import com.smartboox.travel.appimplementation.manager.UserManager;
import com.smartboox.travel.core.adapter.OnItemButtonClickListener;
import com.smartboox.travel.core.view.gridview.AnimationGridView;
import com.smartboox.travel.core.view.gridview.OnGridHiddenListener;
import com.smartboox.travel.utils.DummyCreator;

import java.util.List;

public class HomeFragment extends AppFragment implements AdapterView.OnItemClickListener, OnItemButtonClickListener {
    private UserManager mUserManager;
    private PlaceManager mPlaceManager;
    private AnimationGridView mPlaceGrid;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity.setDrawerEnabled(true);
        mActivity.getToolbar().setToolbarVisibility(View.VISIBLE);
    }

    @Override
    protected void bindView(View rootView) {
        mPlaceGrid = (AnimationGridView) rootView.findViewById(R.id.gv_home_place_grid);
        mPlaceGrid.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        mUserManager = new UserManager(getActivity());
        mPlaceManager = new PlaceManager();

        mActivity.reloadMenu(mUserManager.getLocalUser());
        mActivity.getToolbar().reloadToolbar("Where are you travelling?");

        List<TravelPlace> placeList = mPlaceManager.getTravelPlaceList();
        if (placeList == null || placeList.size() <= 0) {
            placeList = DummyCreator.createTravelPlaceList();
            mPlaceManager.saveTravelPlaceList(placeList);
        }

        PlaceGridAdapter adapter = new PlaceGridAdapter(getActivity(), placeList);
        adapter.setOnItemButtonClickListener(this);
        mPlaceGrid.showGrid(adapter, R.anim.anim_scale_appear);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        mPlaceGrid.hideGrid(R.anim.anim_scale_disappear, new OnGridHiddenListener() {
            @Override
            public void onHidden() {
                getNavigator().navigateTo(new LocationListFragment(), (Parcelable) mPlaceGrid.getItemAtPosition(position));
            }
        });
    }

    @Override
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_place_map:
                TravelPlace place = (TravelPlace) view.getTag();
                getNavigator().navigateTo(new PlaceMapFragment(), place);
                break;
            default:
                break;
        }
    }
}
