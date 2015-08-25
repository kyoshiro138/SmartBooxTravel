package com.smartboox.travel.appimplementation.manager;

import android.content.Context;

import com.smartboox.travel.appimplementation.AppConstant;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;
import com.smartboox.travel.utils.ApplicationPreference;

public class LoginManager {

    ApplicationPreference mPreference;

    public LoginManager(Context context) {
        mPreference = new ApplicationPreference(context);
    }

    public User getUserBasicInfo(String username) {
        if (username.equals(AppConstant.TEST_USERNAME)) {
            return new User(1, AppConstant.TEST_USERNAME, 1);
        }
        return null;
    }

    public User createGuestUser(String username) {
        return new User(0, username, 0);
    }

    public User authenticate(String username, String password) {
        boolean isSuccess = username.equals(AppConstant.TEST_USERNAME) && password.equals(AppConstant.TEST_PASSWORD);

        if (isSuccess) {
            User user = new User(1, AppConstant.TEST_USERNAME, 1);
            String authKey = "key";

            ActiveAndroidDatabaseHelper.saveItem(user);
            mPreference.saveValue(AppConstant.PREFERENCE_SIGNED_IN, true, ApplicationPreference.PREFERENCE_TYPE_BOOLEAN);
            mPreference.saveValue(AppConstant.PREFERENCE_AUTH_KEY, authKey, ApplicationPreference.PREFERENCE_TYPE_STRING);

            return user;
        }
        return null;
    }
}
