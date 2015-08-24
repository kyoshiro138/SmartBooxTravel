package com.smartboox.travel.app.login;

import android.view.View;
import android.widget.ImageView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.core.animation.BaseAnimator;
import com.smartboox.travel.core.animation.FadeInAnimator;
import com.smartboox.travel.core.animation.FadeOutAnimator;
import com.smartboox.travel.core.animation.OnAnimationBeginListener;
import com.smartboox.travel.core.animation.OnAnimationFinishedListener;
import com.smartboox.travel.core.animation.ResizeAnimator;
import com.smartboox.travel.core.animation.ZoomOutAnimator;
import com.smartboox.travel.core.view.textfield.SingleLineTextField;

public class LoginFragment extends AppFragment
        implements View.OnClickListener, OnAnimationFinishedListener, OnAnimationBeginListener {
    private SingleLineTextField mTextFieldUsername;
    private SingleLineTextField mTextFieldPassword;
    private View mLayoutLogin;
    private View mLayoutUsername;
    private View mLayoutPassword;
    private ImageView mImgViewAvatar;

    private ZoomOutAnimator mZoomAnimator;
    private FadeInAnimator mFadeInAnimator;
    private FadeOutAnimator mFadeOutAnimator;
    private FadeOutAnimator mAvatarFadeOutAnimator;
    private ResizeAnimator mResizeAnimator;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    protected void bindView(final View rootView) {
        mLayoutLogin = rootView.findViewById(R.id.layout_login);
        mLayoutUsername = rootView.findViewById(R.id.layout_login_username);
        mLayoutPassword = rootView.findViewById(R.id.layout_login_password);

        mImgViewAvatar = (ImageView) rootView.findViewById(R.id.img_login_avatar);
        mTextFieldUsername = (SingleLineTextField) rootView.findViewById(R.id.tf_login_username);
        mTextFieldPassword = (SingleLineTextField) rootView.findViewById(R.id.tf_login_password);

        rootView.findViewById(R.id.btn_login_next).setOnClickListener(this);
        rootView.findViewById(R.id.btn_login_sign_in).setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        mTextFieldUsername.setHint(R.string.s_screen_login_username);
        mTextFieldPassword.setHint(R.string.s_screen_login_password);
    }

    @Override
    protected void onDisplayed() {
        super.onDisplayed();

        if (mZoomAnimator == null) {
            mZoomAnimator = new ZoomOutAnimator();
            mZoomAnimator.setAnimationView(mLayoutLogin);
            mZoomAnimator.setOnAnimationFinishedListener(this);
        }
        if (mFadeOutAnimator == null) {
            mFadeOutAnimator = new FadeOutAnimator();
            mFadeOutAnimator.setAnimationView(mLayoutUsername);
            mFadeOutAnimator.setOnAnimationBeginListener(this);

            mLayoutPassword.setVisibility(View.VISIBLE);
            mLayoutPassword.setAlpha(0.0f);
        }
        if (mFadeInAnimator == null) {
            mFadeInAnimator = new FadeInAnimator();
            mFadeInAnimator.setOnAnimationFinishedListener(this);
        }
        if (mAvatarFadeOutAnimator == null) {
            mAvatarFadeOutAnimator = new FadeOutAnimator("AVATAR_FADE_OUT");
            mAvatarFadeOutAnimator.setAnimationView(mImgViewAvatar);
        }
        if (mResizeAnimator == null) {
            mResizeAnimator = new ResizeAnimator();
        }

        mZoomAnimator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_next:
                int widthChange = mLayoutPassword.getWidth() - mLayoutUsername.getWidth();
                int heightChange = mLayoutPassword.getHeight() - mLayoutUsername.getHeight();
                mResizeAnimator.setAnimationView(mLayoutLogin);
                mResizeAnimator.setSizeChange(widthChange, heightChange);
                mResizeAnimator.start();

                mFadeInAnimator.setAnimationView(mLayoutUsername);
                mFadeOutAnimator.setAnimationView(mLayoutPassword);
                mFadeInAnimator.start();
                break;
            case R.id.btn_login_sign_in:
                widthChange = mLayoutUsername.getWidth() - mLayoutPassword.getWidth();
                heightChange = mLayoutUsername.getHeight() - mLayoutPassword.getHeight();
                mResizeAnimator.setAnimationView(mLayoutLogin);
                mResizeAnimator.setSizeChange(widthChange, heightChange);
                mResizeAnimator.start();

                mFadeInAnimator.setAnimationView(mLayoutPassword);
                mFadeOutAnimator.setAnimationView(mLayoutUsername);
                mFadeInAnimator.start();
                break;
            default:
                break;
        }
    }

    @Override
    public void onAnimationFinished(BaseAnimator animator, View animationView) {
        String tag = animator.getTag();

        if (tag.equals(mZoomAnimator.getTag())) {
            mFadeOutAnimator.start();
            mAvatarFadeOutAnimator.start();
        } else if (tag.equals(mFadeInAnimator.getTag())) {
            animationView.setVisibility(View.GONE);
            mFadeOutAnimator.start();
        }

    }

    @Override
    public void onAnimationBegin(BaseAnimator animator, View animationView) {
        String tag = animator.getTag();

        if (tag.equals(mFadeOutAnimator.getTag())) {
            animationView.setVisibility(View.VISIBLE);
        }
    }
}
