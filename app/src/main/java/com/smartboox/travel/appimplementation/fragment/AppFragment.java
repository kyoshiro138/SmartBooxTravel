package com.smartboox.travel.appimplementation.fragment;

import android.app.Activity;
import android.os.Bundle;

import com.smartboox.travel.appimplementation.activity.AppDrawerActivity;
import com.smartboox.travel.core.fragment.BaseFragment;
import com.smartboox.travel.core.navigator.Navigable;
import com.smartboox.travel.core.navigator.NavigationFragment;

public abstract class AppFragment extends BaseFragment
        implements NavigationFragment<AppDrawerActivity> {
    protected AppDrawerActivity mActivity;

    @Override
    public AppDrawerActivity getNavigationActivity() {
        return mActivity;
    }

    protected final Navigable getNavigator() {
        return mActivity.getNavigator();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof AppDrawerActivity) {
            mActivity = (AppDrawerActivity) activity;
        } else {
            throw new ClassCastException();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity.setDrawerEnabled(false);
    }
}
