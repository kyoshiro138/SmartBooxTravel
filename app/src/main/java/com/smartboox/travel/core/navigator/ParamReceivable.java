package com.smartboox.travel.core.navigator;

import android.os.Parcelable;

public interface ParamReceivable<TParam extends Parcelable> {
    void receiveParam(TParam param);
}
