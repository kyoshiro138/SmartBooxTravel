package com.smartboox.travel.core.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class FadeOutAnimator extends BaseAnimator implements ValueAnimator.AnimatorUpdateListener {
    public FadeOutAnimator(View view) {
        super(view);
    }

    public FadeOutAnimator(View view, String tag) {
        super(view, tag);
    }

    @Override
    protected Animator initAnimation(View view) {
        ValueAnimator appearAnimation = ValueAnimator.ofFloat(0.0f, 1.0f);
        appearAnimation.setDuration(500);
        appearAnimation.addUpdateListener(this);
        appearAnimation.setInterpolator(new AccelerateInterpolator());

        view.setAlpha(0.0f);

        return appearAnimation;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float alpha = (Float) animation.getAnimatedValue();

        mAnimationView.setAlpha(alpha);
    }
}
