package com.smartboox.travel.app.intro;

import android.view.View;

import com.smartboox.travel.R;
import com.smartboox.travel.app.home.HomeFragment;
import com.smartboox.travel.app.login.LoginFragment;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.UserManager;

public class IntroFragment extends AppFragment {
    private static final int INTRO_TIME_MILLISECONDS = 3 * 1000;

    private UserManager mManager;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_intro;
    }

    @Override
    protected void bindView(View rootView) {

    }

    @Override
    protected void loadData() {
        mManager = new UserManager(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();

        User user = mManager.getLocalUser();
        if (mManager.isSignedIn() && user != null) {
            if (user.getUserType() > 0) {
                // TODO: Call get user profile service
                user = mManager.getUserProfile(user.getUserId());
                if (user != null) {
                    navigateToHomeScreen();
                }
            } else {
                // Guest user
                navigateToHomeScreen();
            }
        } else {
            navigateToLoginScreen();
        }
    }

    private void navigateToLoginScreen() {
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getNavigator().navigateToFirstLevelFragment(new LoginFragment(), null);
            }
        }, INTRO_TIME_MILLISECONDS);
    }

    private void navigateToHomeScreen() {
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getNavigator().navigateToFirstLevelFragment(new HomeFragment(), null);
            }
        }, INTRO_TIME_MILLISECONDS);
    }
}
