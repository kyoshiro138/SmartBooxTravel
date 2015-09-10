package com.smartboox.travel.core.view.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.widget.GridView;
import android.widget.ListAdapter;

public class AnimationGridView extends GridView implements Animation.AnimationListener {
    private OnGridHiddenListener mListener;

    public AnimationGridView(Context context) {
        super(context);
        initView();
    }

    public AnimationGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AnimationGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setLayoutAnimationListener(this);
    }

    public void showGrid(ListAdapter adapter, int enterAnimationId) {
        if (enterAnimationId > 0) {
            setLayoutAnimation(loadLayoutAnimation(enterAnimationId));
        }
        setAdapter(adapter);
    }

    public void hideGrid(int exitAnimationId, OnGridHiddenListener listener) {
        if (exitAnimationId > 0) {
            mListener = listener;
            setLayoutAnimation(loadLayoutAnimation(exitAnimationId));
            startLayoutAnimation();
        }
    }

    private GridLayoutAnimationController loadLayoutAnimation(int animationId) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), animationId);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);

        GridLayoutAnimationController layoutAnimation = new GridLayoutAnimationController(animation);
        layoutAnimation.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layoutAnimation.setDelay(0.2f);
        layoutAnimation.setDirection(GridLayoutAnimationController.DIRECTION_LEFT_TO_RIGHT);
        layoutAnimation.setInterpolator(getContext(), android.R.interpolator.linear);
        return layoutAnimation;
    }

    private void disableViewInteraction() {
        setClickable(false);
        setEnabled(false);
    }

    private void enableViewInteraction() {
        setClickable(true);
        setEnabled(true);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        disableViewInteraction();
    }


    @Override
    public void onAnimationEnd(Animation animation) {
        enableViewInteraction();
        if (mListener != null) {
            mListener.onHidden();
            mListener = null;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
