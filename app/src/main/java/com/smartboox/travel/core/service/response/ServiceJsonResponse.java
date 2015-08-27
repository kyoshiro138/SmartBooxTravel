package com.smartboox.travel.core.service.response;

import java.io.IOException;

public class ServiceJsonResponse<TResponseObject> extends BaseJsonResponse<TResponseObject> {
    public ServiceJsonResponse(String response, TResponseObject responseObject) throws IOException {
        super(response, responseObject);
    }
}
