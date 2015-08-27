package com.smartboox.travel.appimplementation.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "user")
public class User extends Model {
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_USERNAME = "KEY_USERNAME";
    private static final String KEY_USER_TYPE = "KEY_USER_TYPE";

    @Column(name = KEY_USER_ID)
    @JsonProperty("userId")
    private int mUserId;
    @Column(name = KEY_USERNAME)
    @JsonProperty("username")
    private String mUsername;
    @Column(name = KEY_USER_TYPE)
    @JsonProperty("userType")
    private int mUserType;

    public User() {
        super();
    }

    public User(int userId, String username, int userType) {
        super();
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

    @Override
    public String toString() {
        return String.format("USER: [ID:%d][USERNAME:%s][TYPE:%d]", getUserId(), getUsername(), getUserType());
    }
}
