package com.smartboox.travel.appimplementation.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.smartboox.travel.core.navigator.ParamReceivable;

public abstract class AppParamFragment<TParam extends Parcelable> extends AppFragment
        implements ParamReceivable<TParam> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            TParam param = bundle.getParcelable(getClass().getSimpleName());

            receiveParam(param);
        }
    }
}
