package com.smartboox.travel.appimplementation.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "user")
public class User extends Model {
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_USERNAME = "KEY_USERNAME";
    private static final String KEY_USER_TYPE = "KEY_USER_TYPE";

    @Column(name = KEY_USER_ID)
    private int mUserId;
    @Column(name = KEY_USERNAME)
    private String mUsername;
    @Column(name = KEY_USER_TYPE)
    private int mUserType;

    public User(int userId, String username, int userType) {
        mUserId = mUserId;
        mUsername = username;
        mUserType = userType;
    }

    public int getUserId() {
        return mUserId;
    }

    public String getUsername() {
        return mUsername;
    }

    public int getUserType() {
        return mUserType;
    }
}
