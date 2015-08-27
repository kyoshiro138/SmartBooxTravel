package com.smartboox.travel.appimplementation.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartboox.travel.appimplementation.domain.model.User;

public class GetBasicInfoData {
    @JsonProperty("user")
    private User mUser;

    public User getUser() {
        return mUser;
    }
}
