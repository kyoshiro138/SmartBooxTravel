package com.smartboox.travel.appimplementation.domain.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "user")
public class User extends Model {
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_TYPE = "userType";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_AVATAR_URL = "avatarUrl";
    private static final String KEY_DOB = "dateOfBirth";
    private static final String KEY_GENDER = "gender";

    @Column(name = KEY_USER_ID)
    @JsonProperty(KEY_USER_ID)
    private int mUserId;
    @Column(name = KEY_USERNAME)
    @JsonProperty(KEY_USERNAME)
    private String mUsername;
    @Column(name = KEY_USER_TYPE)
    @JsonProperty(KEY_USER_TYPE)
    private int mUserType;
    @Column(name = KEY_FIRST_NAME)
    @JsonProperty(KEY_FIRST_NAME)
    private String mFirstName;
    @Column(name = KEY_LAST_NAME)
    @JsonProperty(KEY_LAST_NAME)
    private String mLastName;
    @Column(name = KEY_AVATAR_URL)
    @JsonProperty(KEY_AVATAR_URL)
    private String mAvatarUrl;
    @JsonProperty(KEY_DOB)
    private String mDateOfBirth;
    @JsonProperty(KEY_GENDER)
    private String mGender;

    public User() {
        super();
    }

    public User(int userId, String username, int userType, String firstName, String lastName, String avatarUrl, String gender) {
        super();
        mUserId = mUserId;
        mUsername = username;
        mUserType = userType;
        mFirstName = firstName;
        mLastName = lastName;
        mAvatarUrl = avatarUrl;
        mGender = gender;
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

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getAvatarUrl() { return mAvatarUrl; }

    public String getDateOfBirth() { return mDateOfBirth; }

    public String getGender() { return mGender; }

    @Override
    public String toString() {
        return String.format("USER: [ID:%d][USERNAME:%s][TYPE:%d]", getUserId(), getUsername(), getUserType());
    }

    private class Gender {
        public static final String MALE = "MALE";
        public static final String FEMALE = "FEMALE";
    }
}
