package com.smartboox.travel.core.service.response;

import java.io.IOException;

public class ServiceJsonResponse<TResponseData> extends BaseJsonResponse<TResponseData> {
    public ServiceJsonResponse(String response, TResponseData responseData) throws IOException {
        super(response, responseData);
    }
}
