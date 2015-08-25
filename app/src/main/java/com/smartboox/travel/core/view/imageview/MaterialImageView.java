package com.smartboox.travel.core.view.imageview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.smartboox.travel.utils.MaterialImageLoadingEvaluator;

public class MaterialImageView extends ImageView implements ValueAnimator.AnimatorUpdateListener {
    private static final int IMAGE_LOADING_DURATION = 1000;

    private AnimateColorMatrixColorFilter mColorFilter;
    private ObjectAnimator mAnimator;
    private BitmapDrawable mDrawable;

    protected Context mContext;

    public MaterialImageView(Context context) {
        super(context);
        initView(context);
    }

    public MaterialImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MaterialImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    protected void initView(Context context) {
        mContext = context;

        MaterialImageLoadingEvaluator mEvaluator = new MaterialImageLoadingEvaluator();
        mColorFilter = new AnimateColorMatrixColorFilter(mEvaluator.getColorMatrix());
        mAnimator = ObjectAnimator.ofObject(mColorFilter, "colorMatrix", mEvaluator, mEvaluator.getColorMatrix());
        mAnimator.addUpdateListener(this);
        mAnimator.setDuration(IMAGE_LOADING_DURATION);
    }

    public void setMaterialImageResource(int resId) {
        mDrawable = (BitmapDrawable) mContext.getResources().getDrawable(resId);
        setMaterialImageDrawable(mDrawable);
    }

    public void setMaterialImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);

        if (drawable != null) {
            mDrawable = (BitmapDrawable) drawable;
            mDrawable.setColorFilter(mColorFilter.getColorFilter());
            mAnimator.start();
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (mDrawable != null) {
            mDrawable.setColorFilter(mColorFilter.getColorFilter());
        }
    }

    private class AnimateColorMatrixColorFilter {
        private ColorMatrixColorFilter mFilter;
        private ColorMatrix mMatrix;

        public AnimateColorMatrixColorFilter(ColorMatrix matrix) {
            setColorMatrix(matrix);
        }

        public ColorMatrixColorFilter getColorFilter() {
            return mFilter;
        }

        public void setColorMatrix(ColorMatrix matrix) {
            mMatrix = matrix;
            mFilter = new ColorMatrixColorFilter(matrix);
        }

        public ColorMatrix getColorMatrix() {
            return mMatrix;
        }
    }
}
