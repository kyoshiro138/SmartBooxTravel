package com.smartboox.travel.appimplementation.service;

import android.content.Context;

import com.android.volley.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartboox.travel.core.service.client.BaseRestClient;
import com.smartboox.travel.core.service.client.OnServiceResponseListener;
import com.smartboox.travel.core.service.request.BaseRequest;
import com.smartboox.travel.core.service.request.ServiceRequest;
import com.smartboox.travel.core.service.response.BaseJsonResponse;
import com.smartboox.travel.core.service.response.ServiceJsonResponse;

import java.io.IOException;

public class AppRestClient<TResponseObject extends AppResponseObject> extends BaseRestClient<AppResponseObject> {
    private Class<TResponseObject> mObjectClass;

    public AppRestClient(Context context, String tag, String url, Class<TResponseObject> objectClass, OnServiceResponseListener<AppResponseObject> listener) {
        super(context, tag, url, listener);
        mObjectClass = objectClass;
    }

    @Override
    protected BaseJsonResponse<AppResponseObject> createResponse(String responseString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TResponseObject responseObject = mapper.readValue(responseString, mObjectClass);
        return new ServiceJsonResponse<AppResponseObject>(responseString, responseObject);
    }

    @Override
    protected BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new ServiceRequest(method, url, listener, errorListener);
    }
}
