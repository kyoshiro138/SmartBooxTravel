package com.smartboox.travel.appimplementation.navigator;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.smartboox.travel.core.navigator.ParamTransferable;
import com.smartboox.travel.core.navigator.menu.MenuActivity;
import com.smartboox.travel.core.navigator.menu.MenuNavigator;

public class AppNavigator extends MenuNavigator implements ParamTransferable {

    public AppNavigator(int contentFragmentId, FragmentManager fragmentManager, MenuActivity activity) {
        super(contentFragmentId, fragmentManager, activity);
    }

    @Override
    public void transferParam(Fragment fragment, Parcelable param) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(fragment.getClass().getSimpleName(), param);

        fragment.setArguments(bundle);
    }
}
