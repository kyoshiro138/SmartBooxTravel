package com.smartboox.travel.core.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

public class ZoomOutAnimator extends BaseAnimator implements ValueAnimator.AnimatorUpdateListener {
    private int mViewWidth;
    private int mViewHeight;
    private float mViewYPosition;

    public ZoomOutAnimator(View view) {
        super(view);
    }

    public ZoomOutAnimator(View view, String tag) {
        super(view, tag);
    }

    @Override
    protected Animator initAnimation(View view) {
        mViewWidth = view.getMeasuredWidth();
        mViewHeight = view.getMeasuredHeight();
        mViewYPosition = view.getY();

        ValueAnimator appearAnimation = ValueAnimator.ofInt(0, 100);
        appearAnimation.setDuration(500);
        appearAnimation.addUpdateListener(this);
        appearAnimation.setInterpolator(new AccelerateInterpolator());

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        layoutParams.width = 0;
        layoutParams.height = 0;

        view.setLayoutParams(layoutParams);

        return appearAnimation;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int val = (Integer) animation.getAnimatedValue();
        ViewGroup.LayoutParams layoutParams = mAnimationView.getLayoutParams();

        int width = mViewWidth * val / 100;
        int height = mViewHeight * val / 100;
        float y = mViewYPosition + ((mViewHeight - height) / 2);

        layoutParams.width = width;
        layoutParams.height = height;

        mAnimationView.setLayoutParams(layoutParams);
        mAnimationView.setY(y);
    }
}
