package com.smartboox.travel.core.navigator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * BaseParamFragment is used to receive parameter from {@link ParamTransferable} when navigation
 *
 * @param <TParam> Param which is received from {@link ParamTransferable} class when navigation
 */
public abstract class BaseParamFragment<TParam extends Parcelable>
        extends Fragment
        implements ParamReceivable<TParam> {

    protected View mRootView;
    protected TParam mParam;

    protected abstract int getFragmentLayoutResource();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getFragmentLayoutResource(), container, false);

        bindView(mRootView);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mParam = bundle.getParcelable(getClass().getSimpleName());

            receiveParam(mParam);
        }
    }

    protected void showToast(CharSequence text) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    }

    protected void logDebug(String log) {
        Log.d(getActivity().getPackageName(), log);
    }

    protected abstract void bindView(View rootView);
}

