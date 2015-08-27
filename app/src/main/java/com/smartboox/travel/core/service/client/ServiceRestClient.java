package com.smartboox.travel.core.service.client;

import android.content.Context;

import com.android.volley.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartboox.travel.core.service.request.BaseRequest;
import com.smartboox.travel.core.service.request.ServiceRequest;
import com.smartboox.travel.core.service.response.ServiceJsonResponse;

import java.io.IOException;

public class ServiceRestClient extends BaseRestClient<ServiceJsonResponse> {
    public ServiceRestClient(Context context, String tag, String url, Class<?> responseDataType, OnServiceResponseListener<ServiceJsonResponse> listener) {
        super(context, tag, url, responseDataType, listener);
    }

    @Override
    protected <TResponseData> ServiceJsonResponse createResponse(String responseString, Class<TResponseData> responseDataType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TResponseData responseData = mapper.readValue(responseString, responseDataType);
        return new ServiceJsonResponse<>(responseString, responseData);
    }

    @Override
    protected BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new ServiceRequest(method, url, listener, errorListener);
    }
}
