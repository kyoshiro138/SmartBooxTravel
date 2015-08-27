package com.smartboox.travel.core.service.response;

import java.io.IOException;

public abstract class BaseJsonResponse<TResponseData> extends BaseResponse {
    private TResponseData mResponseData;

    public TResponseData getResponseData() {
        return mResponseData;
    }

    public BaseJsonResponse(String response, TResponseData responseData) throws IOException {
        super(response);
        mResponseData = responseData;
    }
}
