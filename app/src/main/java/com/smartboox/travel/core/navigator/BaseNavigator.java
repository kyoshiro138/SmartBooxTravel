package com.smartboox.travel.core.navigator;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class BaseNavigator implements Navigable {
    protected int mContentFragmentId;
    protected FragmentManager mFragmentManager;

    public BaseNavigator(int contentFragmentId, FragmentManager fragmentManager) {
        mContentFragmentId = contentFragmentId;
        mFragmentManager = fragmentManager;
    }

    protected void addParam(Fragment fragment, Parcelable param) {
        if (param != null) {
            if (this instanceof ParamTransferable) {
                ((ParamTransferable) this).transferParam(fragment, param);
            }
        }
    }

    public void navigateTo(Fragment fragment, @Nullable Parcelable param) {
        addParam(fragment, param);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
        transaction.replace(mContentFragmentId, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void navigateToFirstLevelFragment(Fragment fragment, @Nullable Parcelable param) {
        mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        navigateTo(fragment, param);
    }

    public final void navigateBack() {
        mFragmentManager.popBackStack();
        refresh();
    }

    public final void navigateBack(int count) {
        for (int i = 0; i < count && mFragmentManager.getBackStackEntryCount() - i > 1; i++) {
            mFragmentManager.popBackStack();
        }
        refresh();
    }

    public final void navigateBackToFirstLevelFragment() {
        mFragmentManager.popBackStackImmediate(1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        refresh();
    }

    private void refresh() {
        Fragment fragment = mFragmentManager.findFragmentById(mContentFragmentId);
        if (fragment != null && fragment instanceof Refreshable) {
            ((Refreshable) fragment).onBackRefresh();
        }
    }
}
