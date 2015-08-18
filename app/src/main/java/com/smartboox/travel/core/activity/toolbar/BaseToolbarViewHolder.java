package com.smartboox.travel.core.activity.toolbar;

import android.content.Context;
import android.view.View;

public abstract class BaseToolbarViewHolder {
    protected Context mContext;
    protected View mToolbarView;

    public BaseToolbarViewHolder(Context context, View toolbarView) {
        mContext = context;
        mToolbarView = toolbarView;

        initToolbarView(toolbarView);
    }

    protected abstract void initToolbarView(View toolbarView);

    public void setToolbarVisibility(int visibility) {
        if (mToolbarView != null) {
            mToolbarView.setVisibility(visibility);
        }
    }
}
