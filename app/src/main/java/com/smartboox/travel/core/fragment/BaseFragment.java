package com.smartboox.travel.core.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment implements ViewTreeObserver.OnGlobalLayoutListener {
    protected View mRootView;

    protected abstract int getFragmentLayoutResource();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getFragmentLayoutResource(), container, false);

        bindView(mRootView);

        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadData();
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

    protected abstract void loadData();

    protected void onDisplayed() {

    }

    @Override
    public void onGlobalLayout() {
        // Removing layout listener to avoid multiple calls
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            mRootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        } else {
            mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        onDisplayed();
    }
}
