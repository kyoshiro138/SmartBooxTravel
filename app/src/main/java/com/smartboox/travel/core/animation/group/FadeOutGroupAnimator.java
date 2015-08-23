package com.smartboox.travel.core.animation.group;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.List;

public class FadeOutGroupAnimator extends BaseGroupAnimator implements ValueAnimator.AnimatorUpdateListener {
    public FadeOutGroupAnimator(List<View> viewList) {
        super(viewList);
    }

    public FadeOutGroupAnimator(List<View> viewList, String tag) {
        super(viewList, tag);
    }

    @Override
    protected Animator initAnimation(List<View> viewList) {
        ValueAnimator appearAnimation = ValueAnimator.ofFloat(0.0f, 1.0f);
        appearAnimation.setDuration(500);
        appearAnimation.addUpdateListener(this);
        appearAnimation.setInterpolator(new AccelerateInterpolator());

        for (View view : viewList) {
            view.setAlpha(0.0f);
        }

        return appearAnimation;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float alpha = (Float) animation.getAnimatedValue();

//        for (View view : mAnimationViewList) {
//            Log.d("ANIMATE", "" + alpha);
            mAnimationViewList.get(0).setAlpha(alpha);
//        }
    }
}
