package com.smartboox.travel.appimplementation.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.appimplementation.service.AppResponseObject;

public class GetProfileResponseObject
        extends AppResponseObject<GetProfileResponseObject.GetProfileResponseData> {
    public class GetProfileResponseData {
        @JsonProperty("user")
        private User mUser;

        public User getUser() {
            return mUser;
        }
    }
}
