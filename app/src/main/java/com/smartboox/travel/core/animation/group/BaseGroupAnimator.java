package com.smartboox.travel.core.animation.group;

import android.animation.Animator;
import android.view.View;

import com.smartboox.travel.core.animation.BaseAnimator;

import java.util.List;

public abstract class BaseGroupAnimator extends BaseAnimator {
    private Animator mAnimator;

    protected List<View> mAnimationViewList;

    public BaseGroupAnimator(View view) {
        super(view);
    }

    public BaseGroupAnimator(View view, String tag) {
        super(view, tag);
    }

    public BaseGroupAnimator(List<View> viewList) {
        super(null);
        mAnimationViewList = viewList;

        mAnimator = initAnimation(mAnimationViewList);
        if (mAnimator != null) {
            mAnimator.addListener(this);
        }
    }

    public BaseGroupAnimator(List<View> viewList, String tag) {
        super(null, tag);
        mAnimationViewList = viewList;

        mAnimator = initAnimation(mAnimationViewList);
        if (mAnimator != null) {
            mAnimator.addListener(this);
        }
    }

    protected abstract Animator initAnimation(List<View> viewList);

    @Override
    protected final Animator initAnimation(View view) {
        return null;
    }
}
