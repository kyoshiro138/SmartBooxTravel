package com.smartboox.travel.core.service.client;

import com.android.volley.VolleyError;

public interface OnServiceResponseListener<T> {
    void onResponseSuccess(String tag, T response);

    void onResponseFailed(String tag, VolleyError error);

    void onParseError(String tag, String response);
}
