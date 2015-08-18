package com.smartboox.travel.core.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public abstract class BaseFragmentActivity extends FragmentActivity {
    private boolean mExitAppOnBack = false;
    private int mExitAppOnBackDelay = 2000;

    protected abstract int getActivityLayoutResource();

    protected abstract int getContentFragmentId();

    protected final void setExitAppOnBack(boolean enabled) {
        mExitAppOnBack = enabled;
    }

    protected final void setExitAppOnBackDelay(int millisecond) {
        mExitAppOnBackDelay = millisecond;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutResource());

        initContentFragment();
    }

    protected void initContentFragment() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentById(getContentFragmentId());
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            if (mExitAppOnBack) {
                finish();
            } else {
                mExitAppOnBack = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mExitAppOnBack = false;
                    }
                }, mExitAppOnBackDelay);
            }
        }
    }
}
