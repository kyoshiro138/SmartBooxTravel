package com.smartboox.travel.core.service.client;

import android.content.Context;

import com.android.volley.Response;
import com.smartboox.travel.core.service.request.BaseRequest;
import com.smartboox.travel.core.service.request.ServiceRequest;
import com.smartboox.travel.core.service.response.BaseResponse;
import com.smartboox.travel.core.service.response.ServiceResponse;

public class ServiceClient extends BaseServiceClient {
    public ServiceClient(Context context, String tag, String url) {
        super(context, tag, url);
    }

    @Override
    protected BaseResponse createResponse(String responseString) {
        return new ServiceResponse(responseString);
    }

    @Override
    protected BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new ServiceRequest(method, url, listener, errorListener);
    }
}
