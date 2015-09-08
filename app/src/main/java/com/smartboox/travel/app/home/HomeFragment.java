package com.smartboox.travel.app.home;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.UserManager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends AppFragment {
    private UserManager mUserManager;
    private GridView mPlaceGrid;

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
        mPlaceGrid = (GridView) rootView.findViewById(R.id.gv_home_place_grid);
    }

    @Override
    protected void loadData() {
        mUserManager = new UserManager(getActivity());
        mActivity.reloadMenu(mUserManager.getLocalUser());

        mActivity.getToolbar().reloadToolbar("Where are you travelling?");

        List<String> data = new ArrayList<>();
        data.add("test");
        data.add("test");
        data.add("test");
        data.add("test");
        data.add("test");
        data.add("test");
        data.add("test");
        PlaceGridAdapter adapter = new PlaceGridAdapter(getActivity(), data);
        mPlaceGrid.setAdapter(adapter);
    }
}
