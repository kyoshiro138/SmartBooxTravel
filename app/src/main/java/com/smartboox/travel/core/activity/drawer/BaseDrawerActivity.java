package com.smartboox.travel.core.activity.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.smartboox.travel.core.activity.BaseFragmentActivity;

public abstract class BaseDrawerActivity extends BaseFragmentActivity implements DrawerLayout.DrawerListener {
    private DrawerLayout mDrawerLayout;
    private FrameLayout mMenuLayout;

    protected abstract int getDrawerLayoutId();

    protected abstract int getMenuLayoutId();

    protected abstract Fragment getDrawerMenuFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDrawerLayout();
        initMenuLayout();
    }

    private void initDrawerLayout() {
        if (getDrawerLayoutId() > 0) {
            mDrawerLayout = (DrawerLayout) findViewById(getDrawerLayoutId());
            mDrawerLayout.setDrawerListener(this);

            mMenuLayout = (FrameLayout) findViewById(getMenuLayoutId());
        }
    }

    private void initMenuLayout() {
        Fragment menuFragment = getDrawerMenuFragment();

        if (getMenuLayoutId() > 0 && menuFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(getMenuLayoutId(), menuFragment, menuFragment.getClass().getSimpleName());
            transaction.commit();
        }
    }

    public final boolean getDrawerEnabled() {
        return mDrawerLayout.getDrawerLockMode(mMenuLayout) == DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    public final void setDrawerEnabled(boolean isEnabled) {
        if (isEnabled) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    public final void closeDrawer() {
        if (isDrawerOpened()) {
            mDrawerLayout.closeDrawer(mMenuLayout);
        }
    }

    public final boolean isDrawerOpened() {
        if (mDrawerLayout != null) {
            return mDrawerLayout.isDrawerOpen(mMenuLayout);
        }
        return false;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onBackPressed() {
        if(isDrawerOpened()) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
