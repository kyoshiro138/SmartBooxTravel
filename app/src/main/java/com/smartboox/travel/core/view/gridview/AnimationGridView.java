package com.smartboox.travel.core.view.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.widget.GridView;

public class AnimationGridView extends GridView {
    private int mEnterAnimationId = 0;
    private int mExitAnimationId = 0;

    public AnimationGridView(Context context) {
        super(context);
    }

    public AnimationGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEnterAnimation(int animationId) {
        if (animationId > 0) {
            mEnterAnimationId = animationId;
            setLayoutAnimation(loadLayoutAnimation(mEnterAnimationId));
        }
    }

    public void setExitAnimation(int animationId) {
        if (animationId > 0) {
            mExitAnimationId = animationId;
        }
    }

    public void startEnterAnimation() {
        if (mEnterAnimationId > 0) {
            setLayoutAnimation(loadLayoutAnimation(mEnterAnimationId));
            startLayoutAnimation();
        }
    }

    public void startExitAnimation() {
        if (mExitAnimationId > 0) {
            setLayoutAnimation(loadLayoutAnimation(mExitAnimationId));
            startLayoutAnimation();
        }
    }

    private GridLayoutAnimationController loadLayoutAnimation(int animationId) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), animationId);
        GridLayoutAnimationController layoutAnimation = new GridLayoutAnimationController(animation);
        layoutAnimation.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layoutAnimation.setDelay(0.2f);
        layoutAnimation.setDirection(GridLayoutAnimationController.DIRECTION_LEFT_TO_RIGHT);
        layoutAnimation.setInterpolator(getContext(), android.R.interpolator.linear);
        return layoutAnimation;
    }
}
