package com.smartboox.travel.appimplementation.service;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AppResponseObject<TResponseData> {
    @JsonProperty("status")
    private boolean mStatus;

    public boolean getStatus() {
        return mStatus;
    }

    @JsonProperty("code")
    private int mResponseCode;

    public int getResponseCode() {
        return mResponseCode;
    }

    @JsonProperty("message")
    private String mMessage;

    public String getMessage() {
        return mMessage;
    }

    @JsonProperty("response")
    private TResponseData mResponseData;

    public TResponseData getResponseData() {
        return mResponseData;
    }

    public AppResponseObject() {
    }

}
