package com.smartboox.travel.appimplementation.manager;

import android.content.Context;

import com.smartboox.travel.appimplementation.AppConstant;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;
import com.smartboox.travel.utils.ApplicationPreference;

import java.util.List;

public class IntroManager {
    ApplicationPreference mPreference;

    public IntroManager(Context context) {
        mPreference = new ApplicationPreference(context);
    }

    public boolean isSignedIn() {
        return (boolean) mPreference.getValue(AppConstant.PREFERENCE_SIGNED_IN, false, ApplicationPreference.PREFERENCE_TYPE_BOOLEAN);
    }

    public User getLocalUser(String username) {
        List<User> userList = ActiveAndroidDatabaseHelper.getList(User.class);
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                if (user.getUsername().equals(username))
                    return user;
            }
        }
        return null;
    }

    public User getUserProfile(int userId) {
        String key = (String) mPreference.getValue(AppConstant.PREFERENCE_AUTH_KEY, "", ApplicationPreference.PREFERENCE_TYPE_STRING);

        if (userId > 0 && !key.isEmpty()) {
            return new User(1, "user", 1);
        }
        return null;
    }
}
