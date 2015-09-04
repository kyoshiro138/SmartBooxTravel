package com.smartboox.travel.appimplementation.manager;

import android.content.Context;

import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.appimplementation.service.AppResponseObject;
import com.smartboox.travel.appimplementation.service.AppRestClient;
import com.smartboox.travel.appimplementation.service.ServiceConstant;
import com.smartboox.travel.appimplementation.service.response.AuthenticateResponseObject;
import com.smartboox.travel.appimplementation.service.response.GetBasicInfoResponseObject;
import com.smartboox.travel.appimplementation.service.response.GetProfileResponseObject;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;
import com.smartboox.travel.core.dialog.progress.SystemProgressDialog;
import com.smartboox.travel.core.service.client.OnServiceResponseListener;
import com.smartboox.travel.utils.ApplicationPreference;

import java.util.List;

public class UserManager {
    // TEST CONSTANTS
    public static final String TEST_USERNAME = "user";
    public static final String TEST_PASSWORD = "pass";

    // PREFERENCE KEYS
    public static final String PREFERENCE_SIGNED_IN = "PREFERENCE_SIGNED_IN";
    public static final String PREFERENCE_AUTH_KEY = "PREFERENCE_AUTH_KEY";

    // SERVICE KEYS
    public static final String SERVICE_GET_BASIC_INFO = "SERVICE_GET_BASIC_INFO";
    public static final String SERVICE_AUTHENTICATION = "SERVICE_AUTHENTICATION";
    public static final String SERVICE_GET_PROFILE = "SERVICE_GET_PROFILE";

    private Context mContext;
    private ApplicationPreference mPreference;

    public UserManager(Context context) {
        mContext = context;
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

    public void startGetProfile(int userId, OnServiceResponseListener<AppResponseObject> listener) {
        String key = (String) mPreference.getValue(PREFERENCE_AUTH_KEY, "", ApplicationPreference.PREFERENCE_TYPE_STRING);

        if (!key.equals("abc123")) {
            key = "abc";
            userId = 1;
        }

        if (userId != 1) {
            userId = 0;
        }

        String url = String.format("%s?key=%s&userId=%d", ServiceConstant.URL_GET_PROFILE, key, userId);

        AppRestClient<GetProfileResponseObject> restClient = new AppRestClient<>(mContext, SERVICE_GET_PROFILE, url, GetProfileResponseObject.class, listener);
        restClient.executeGet();
    }

    public void startGetBasicInfoService(String username, OnServiceResponseListener<AppResponseObject> listener) {
        if (!username.equals("user")) {
            username = "guest";
        }

        String url = String.format("%s?username=%s", ServiceConstant.URL_GET_BASIC_INFO, username);

        SystemProgressDialog<String> dialog = new SystemProgressDialog<>(mContext);
        dialog.setMessage("Checking username...");

        AppRestClient<GetBasicInfoResponseObject> restClient = new AppRestClient<>(mContext, SERVICE_GET_BASIC_INFO, url, GetBasicInfoResponseObject.class, listener);
        restClient.setProgressDialog(dialog);
        restClient.executeGet();
    }

    public User createGuestUser(String username) {
        return new User(0, username, 0);
    }

    public void authenticate(String username, String password, OnServiceResponseListener<AppResponseObject> listener) {
        if (!username.equals(TEST_USERNAME) || !password.equals(TEST_PASSWORD)) {
            username = "user";
            password = "wrong";
        }

        String url = String.format("%s?username=%s&password=%s", ServiceConstant.URL_AUTHENTICATION, username, password);

        SystemProgressDialog<String> dialog = new SystemProgressDialog<>(mContext);
        dialog.setMessage("Signing in...");

        AppRestClient<AuthenticateResponseObject> restClient = new AppRestClient<>(mContext, SERVICE_AUTHENTICATION, url, AuthenticateResponseObject.class, listener);
        restClient.setProgressDialog(dialog);
        restClient.executeGet();
    }

    public void saveSignIn(User user, String key) {
        clearUser();
        ActiveAndroidDatabaseHelper.saveItem(user);

        mPreference.saveValue(PREFERENCE_SIGNED_IN, true, ApplicationPreference.PREFERENCE_TYPE_BOOLEAN);
        mPreference.saveValue(PREFERENCE_AUTH_KEY, key, ApplicationPreference.PREFERENCE_TYPE_STRING);
    }

    public void signOut() {
        clearUser();
    }

    private void clearUser() {
        // clear user
        ActiveAndroidDatabaseHelper.removeAll(User.class);

        // Clear authentication data
        mPreference.remove(PREFERENCE_SIGNED_IN);
        mPreference.remove(PREFERENCE_AUTH_KEY);
    }
}
