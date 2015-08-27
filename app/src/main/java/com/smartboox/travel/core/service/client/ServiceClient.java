package com.smartboox.travel.core.service.client;

import android.content.Context;

import com.android.volley.Response;
import com.smartboox.travel.core.service.request.BaseRequest;
import com.smartboox.travel.core.service.request.ServiceRequest;
import com.smartboox.travel.core.service.response.ServiceResponse;

public class ServiceClient extends BaseServiceClient<ServiceResponse> {
    public ServiceClient(Context context, String tag, String url, OnServiceResponseListener<ServiceResponse> listener) {
        super(context, tag, url, listener);
    }

    @Override
    protected ServiceResponse createResponse(String responseString) {
        return new ServiceResponse(responseString);
    }

    @Override
    protected BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new ServiceRequest(method, url, listener, errorListener);
    }
}
