package com.smartboox.travel.appimplementation.manager;

import android.content.Context;

import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;
import com.smartboox.travel.utils.ApplicationPreference;

import java.util.List;

public class UserManager {
    //    TEST CONSTANTS
    public static final String TEST_USERNAME = "user";
    public static final String TEST_PASSWORD = "pass";

    //    PREFERENCE KEYS
    public static final String PREFERENCE_SIGNED_IN = "PREFERENCE_SIGNED_IN";
    public static final String PREFERENCE_AUTH_KEY = "PREFERENCE_AUTH_KEY";

    private ApplicationPreference mPreference;

    public UserManager(Context context) {
        mPreference = new ApplicationPreference(context);
    }

    public boolean isSignedIn() {
        return (boolean) mPreference.getValue(PREFERENCE_SIGNED_IN, false, ApplicationPreference.PREFERENCE_TYPE_BOOLEAN);
    }

    public User getLocalUser() {
        List<User> userList = ActiveAndroidDatabaseHelper.getList(User.class);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    public User getUserProfile(int userId) {
        String key = (String) mPreference.getValue(PREFERENCE_AUTH_KEY, "", ApplicationPreference.PREFERENCE_TYPE_STRING);

        if (userId > 0 && !key.isEmpty()) {
            return new User(1, "user", 1);
        }
        return null;
    }

    public User getUserBasicInfo(String username) {
        if (username.equals(TEST_USERNAME)) {
            return new User(1, TEST_USERNAME, 1);
        }
        return null;
    }

    public User createGuestUser(String username) {
        return new User(0, username, 0);
    }

    public User authenticate(String username, String password) {
        boolean isSuccess = username.equals(TEST_USERNAME) && password.equals(TEST_PASSWORD);

        if (isSuccess) {
            User user = new User(1, TEST_USERNAME, 1);
            String authKey = "key";

            ActiveAndroidDatabaseHelper.saveItem(user);
            mPreference.saveValue(PREFERENCE_SIGNED_IN, true, ApplicationPreference.PREFERENCE_TYPE_BOOLEAN);
            mPreference.saveValue(PREFERENCE_AUTH_KEY, authKey, ApplicationPreference.PREFERENCE_TYPE_STRING);

            return user;
        }
        return null;
    }

    public void clearUser() {
        ActiveAndroidDatabaseHelper.removeAll(User.class);
        mPreference.remove(PREFERENCE_SIGNED_IN);
        mPreference.remove(PREFERENCE_AUTH_KEY);
    }
}