package com.smartboox.travel.app.intro;

import android.view.View;

import com.android.volley.VolleyError;
import com.smartboox.travel.R;
import com.smartboox.travel.app.home.HomeFragment;
import com.smartboox.travel.app.login.LoginFragment;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.UserManager;
import com.smartboox.travel.appimplementation.service.AppResponseObject;
import com.smartboox.travel.appimplementation.service.response.GetProfileResponseObject;
import com.smartboox.travel.core.service.client.OnServiceResponseListener;

public class IntroFragment extends AppFragment implements OnServiceResponseListener<AppResponseObject> {
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
        boolean isSignedIn = mManager.isSignedIn();
        boolean haveUser = user != null;
        logDebug(String.format("[SIGNED_IN:%s][USER:%s]", isSignedIn, haveUser));

        if (isSignedIn && haveUser) {
            logDebug(user.toString());
            navigateToHomeScreen();
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

    @Override
    public void onResponseSuccess(String tag, AppResponseObject response) {
        switch (tag) {
            case UserManager.SERVICE_GET_PROFILE:
                onGetProfileResponse((GetProfileResponseObject) response);
                break;
            default:
                break;
        }
    }

    private void onGetProfileResponse(GetProfileResponseObject responseObject) {
        boolean isSuccess = responseObject.getStatus();
        if (isSuccess) {
            navigateToHomeScreen();
        } else {
            int errorCode = responseObject.getResponseCode();
            String message = responseObject.getMessage();
            logDebug(String.format("Error %d: %s", errorCode, message));
            navigateToLoginScreen();
        }
    }

    @Override
    public void onResponseFailed(String tag, VolleyError error) {
        navigateToLoginScreen();
    }

    @Override
    public void onParseError(String tag, String response) {
        navigateToLoginScreen();
    }
}
