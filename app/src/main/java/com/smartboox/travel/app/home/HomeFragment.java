package com.smartboox.travel.app.home;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.UserManager;
import com.smartboox.travel.core.view.gridview.AnimationGridView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends AppFragment implements AdapterView.OnItemClickListener {
    private UserManager mUserManager;
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
        mPlaceGrid.setEnterAnimation(R.anim.anim_scale_appear);
        mPlaceGrid.setExitAnimation(R.anim.anim_scale_disappear);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPlaceGrid.startExitAnimation();
    }
}
