package com.smartboox.travel.core.service.client;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.smartboox.travel.core.service.response.BaseJsonResponse;

import java.io.IOException;

public abstract class BaseRestClient<TResponse extends BaseJsonResponse> extends BaseServiceClient<TResponse> {
    private OnServiceResponseListener<TResponse> mListener;
    private Class<?> mResponseDataType;

    public <TData> BaseRestClient(Context context, String tag, String url, Class<TData> responseDataType, OnServiceResponseListener<TResponse> listener) {
        super(context, tag, url, null);
        mListener = listener;
        mResponseDataType = responseDataType;
    }

    @Override
    public void onResponse(String responseString) {
        try {
            Log.d(mContext.getPackageName(), String.format("[%s RESPONSE: %s]", mTag, responseString));

            if (mListener != null) {
                mListener.onResponseSuccess(mTag, createResponse(responseString, mResponseDataType));
            }
        } catch (Exception e) {
            Log.d(mContext.getPackageName(), String.format("[%s PARSE ERROR]", mTag));
            if (mListener != null) {
                mListener.onParseError(mTag, responseString);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (mListener != null) {
            mListener.onResponseFailed(mTag, error);
        }
    }

    protected abstract <TData> TResponse createResponse(String responseString, Class<TData> responseDataType) throws IOException;

    @Override
    protected final TResponse createResponse(String responseString) {
        return null;
    }
}
