package com.smartboox.travel.app.home;

import android.os.Bundle;
import android.view.View;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;

public class HomeFragment extends AppFragment {


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

    }

    @Override
    protected void loadData() {

    }
}