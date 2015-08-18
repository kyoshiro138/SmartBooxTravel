package com.smartboox.travel.core.navigator.menu;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.smartboox.travel.core.navigator.BaseNavigator;

public abstract class MenuNavigator extends BaseNavigator {
    private MenuActivity mMenuActivity;

    public MenuNavigator(int contentFragmentId, FragmentManager fragmentManager, MenuActivity activity) {
        super(contentFragmentId, fragmentManager);

        mMenuActivity = activity;
    }

    @Override
    public void navigateTo(final Fragment fragment, @Nullable Parcelable param) {
        addParam(fragment, param);

        if (mMenuActivity != null) {
            OnMenuClosedListener listener = new OnMenuClosedListener() {
                @Override
                public void onClosed() {
                    FragmentTransaction transaction = mFragmentManager.beginTransaction();
                    transaction.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
                    transaction.replace(mContentFragmentId, fragment, fragment.getClass().getSimpleName());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            };
            mMenuActivity.closeMenu(listener);
        } else {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
            transaction.replace(mContentFragmentId, fragment, fragment.getClass().getSimpleName());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void navigateToFirstLevelFragment(Fragment fragment, @Nullable Parcelable param) {
        mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        navigateTo(fragment, param);
    }
}
