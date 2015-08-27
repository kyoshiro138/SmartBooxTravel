package com.smartboox.travel.core.service.client;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smartboox.travel.core.service.request.BaseRequest;
import com.smartboox.travel.core.service.request.RequestParam;
import com.smartboox.travel.core.service.response.BaseResponse;

public abstract class BaseServiceClient<T extends BaseResponse> implements Response.ErrorListener, Response.Listener<String> {
    protected Context mContext;
    protected RequestQueue mRequestQueue;
    protected String mUrl;
    protected String mTag;
    private OnServiceResponseListener<T> mListener;

    public BaseServiceClient(Context context, String tag, String url, OnServiceResponseListener<T> listener) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(context);
        mTag = tag;
        mUrl = url;
        mListener = listener;
    }

    public void executeGet() {
        BaseRequest request = createRequest(Request.Method.GET, mUrl, this, this);
        request.setTag(mTag);

        Log.d(mContext.getPackageName(), String.format("[%s REQUEST] [URL: %s]", mTag, mUrl));

        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    public void executePost(RequestParam param) {
        BaseRequest request = createRequest(Request.Method.POST, mUrl, this, this);
        request.setTag(mTag);

        if (param != null) {
            request.addParam(param);
            Log.d(mContext.getPackageName(), String.format("[%s REQUEST] [URL: %s] [PARAM: %s]", mTag, mUrl, param.getRequestParam().toString()));
        }

        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(mContext.getPackageName(), String.format("[%s RESPONSE ERROR]", mTag));
        if (mListener != null) {
            mListener.onResponseFailed(mTag, error);
        }
    }

    @Override
    public void onResponse(String responseString) {
        Log.d(mContext.getPackageName(), String.format("[%s RESPONSE: %s]", mTag, responseString));
        if (mListener != null) {
            mListener.onResponseSuccess(mTag, createResponse(responseString));
        }
    }

    protected abstract T createResponse(String responseString);

    protected abstract BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener);
}
