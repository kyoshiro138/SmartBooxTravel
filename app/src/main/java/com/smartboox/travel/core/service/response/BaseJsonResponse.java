package com.smartboox.travel.core.service.response;

import java.io.IOException;

public abstract class BaseJsonResponse<TResponseObject> extends BaseResponse {
    private TResponseObject mResponseObject;

    public TResponseObject getResponseObject() {
        return mResponseObject;
    }

    public BaseJsonResponse(String responseString, TResponseObject responseObject) throws IOException {
        super(responseString);
        mResponseObject = responseObject;
    }
}
