package com.smartboox.travel.app.intro;

import android.view.View;

import com.smartboox.travel.R;
import com.smartboox.travel.app.login.LoginFragment;
import com.smartboox.travel.appimplementation.fragment.AppFragment;

public class IntroFragment extends AppFragment {
    private static final int INTRO_TIME_MILLISECONDS = 5*1000;
    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_intro;
    }

    @Override
    protected void bindView(View rootView) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getNavigator() != null) {
                    getNavigator().navigateToFirstLevelFragment(new LoginFragment(), null);
                }
            }
        }, INTRO_TIME_MILLISECONDS);
    }
}
