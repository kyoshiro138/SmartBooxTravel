package com.smartboox.travel.appimplementation.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.appimplementation.service.AppResponseObject;

public class AuthenticateResponseObject extends AppResponseObject<AuthenticateResponseObject.AuthenticateResponseData> {
    public class AuthenticateResponseData {
        @JsonProperty("key")
        private String mKey;

        public String getKey() {
            return mKey;
        }

        @JsonProperty("user")
        private User mUser;

        public User getUser() {
            return mUser;
        }
    }
}
