package com.smartboox.travel.appimplementation.activity;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.smartboox.travel.R;
import com.smartboox.travel.core.activity.toolbar.BaseToolbarViewHolder;

public class AppToolbarViewHolder extends BaseToolbarViewHolder {
    private TextView mTitleTextView;
    private ImageButton mMenuButton;
    private View mShadowView;

    public AppToolbarViewHolder(Context context, View toolbarView) {
        super(context, toolbarView);
    }

    @Override
    protected void initToolbarView(View toolbarView) {
        if (toolbarView != null) {
            mTitleTextView = (TextView) toolbarView.findViewById(R.id.toolbar_title_text);
            mMenuButton = (ImageButton) toolbarView.findViewById(R.id.toolbar_navigate_button);
        }
    }

    public void setToolbarShadowView(View shadowView) {
        mShadowView = shadowView;
    }

    @Override
    public void setToolbarVisibility(int visibility) {
        super.setToolbarVisibility(visibility);
        if (mShadowView != null) {
            mShadowView.setVisibility(visibility);
        }
    }

    public void reloadToolbar(String title) {
        mTitleTextView.setText(title);
    }
}
