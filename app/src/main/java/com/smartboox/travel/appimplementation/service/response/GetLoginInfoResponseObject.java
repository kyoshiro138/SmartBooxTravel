package com.smartboox.travel.appimplementation.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.appimplementation.service.AppResponseObject;

public class GetLoginInfoResponseObject extends AppResponseObject<GetLoginInfoResponseObject.GetBasicInfoResponseData> {
    public class GetBasicInfoResponseData {
        @JsonProperty("user")
        private User mUser;

        public User getUser() {
            return mUser;
        }
    }
}
