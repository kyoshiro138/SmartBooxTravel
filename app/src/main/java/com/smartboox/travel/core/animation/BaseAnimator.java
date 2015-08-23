package com.smartboox.travel.core.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;

public abstract class BaseAnimator implements Animator.AnimatorListener {
    private String mTag;
    private OnAnimationEndListener mAnimationEndListener;

    protected View mAnimationView;
    protected Animator mAnimator;

    public BaseAnimator(View view) {
        mAnimationView = view;
        mTag = this.getClass().getSimpleName();

        mAnimator = initAnimation(mAnimationView);
        if (mAnimator != null) {
            mAnimator.addListener(this);
        }
    }

    public BaseAnimator(View view, String tag) {
        mAnimationView = view;
        mTag = tag;

        mAnimator = initAnimation(mAnimationView);
        if (mAnimator != null) {
            mAnimator.addListener(this);
        }
    }

    protected abstract Animator initAnimation(View view);

    public void start() {
        if (mAnimator != null && !mAnimator.isRunning()) {
            mAnimator.start();
        }
    }

    public void setDuration(long milliseconds) {
        if (mAnimator != null) {
            mAnimator.setDuration(milliseconds);
        }
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        if (mAnimator != null) {
            mAnimator.setInterpolator(interpolator);
        }
    }

    public String getTag() {
        return mTag;
    }

    public void setOnAnimationEndListener(OnAnimationEndListener listener) {
        mAnimationEndListener = listener;
    }

    protected int getDefaultDuration() {
        return 500;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (mAnimationEndListener != null) {
            mAnimationEndListener.onAnimationEnded(mTag, animation);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
