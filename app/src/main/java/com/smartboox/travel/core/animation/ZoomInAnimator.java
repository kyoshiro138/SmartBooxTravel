package com.smartboox.travel.core.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

public class ZoomInAnimator extends BaseAnimator implements ValueAnimator.AnimatorUpdateListener {
    private int mViewWidth;
    private int mViewHeight;
    private float mViewYPosition;

    public ZoomInAnimator() {
        super();
    }

    public ZoomInAnimator(String tag) {
        super(tag);
    }

    @Override
    protected Animator initAnimation() {
        ValueAnimator appearAnimation = ValueAnimator.ofInt(100, 0);
        appearAnimation.setDuration(getDefaultDuration());
        appearAnimation.addUpdateListener(this);
        appearAnimation.setInterpolator(new AccelerateInterpolator());

        return appearAnimation;
    }

    @Override
    public void setAnimationView(View view) {
        super.setAnimationView(view);

        mViewWidth = view.getMeasuredWidth();
        mViewHeight = view.getMeasuredHeight();
        mViewYPosition = view.getY();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int val = (Integer) animation.getAnimatedValue();
        int width = mViewWidth * val / 100;
        int height = mViewHeight * val / 100;
        float y = mViewYPosition + ((mViewHeight - height) / 2);

        ViewGroup.LayoutParams layoutParams = mAnimationView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;

        mAnimationView.setLayoutParams(layoutParams);
        mAnimationView.setY(y);
    }
}
