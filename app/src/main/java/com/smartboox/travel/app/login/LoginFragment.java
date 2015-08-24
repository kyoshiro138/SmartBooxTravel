package com.smartboox.travel.app.login;

import android.animation.Animator;
import android.view.View;
import android.widget.ImageView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.core.animation.FadeInAnimator;
import com.smartboox.travel.core.animation.FadeOutAnimator;
import com.smartboox.travel.core.animation.OnAnimationEndListener;
import com.smartboox.travel.core.animation.ZoomOutAnimator;
import com.smartboox.travel.core.view.textfield.SingleLineTextField;

public class LoginFragment extends AppFragment implements OnAnimationEndListener, View.OnClickListener {
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
            mZoomAnimator = new ZoomOutAnimator(mLayoutLogin);
            mZoomAnimator.setOnAnimationEndListener(this);
        }
        if (mFadeOutAnimator == null) {
            mFadeOutAnimator = new FadeOutAnimator(mLayoutUsername);
        }
        if(mFadeInAnimator == null) {
            mFadeInAnimator = new FadeInAnimator(mLayoutPassword);
        }
        if (mAvatarFadeOutAnimator == null) {
            mAvatarFadeOutAnimator = new FadeOutAnimator(mImgViewAvatar, "AVATAR_FADE_OUT");
        }


        mZoomAnimator.start();
    }

    @Override
    public void onAnimationEnded(String tag, Animator animator) {
        if(tag.equals(mZoomAnimator.getTag())) {
            mFadeOutAnimator.start();
            mAvatarFadeOutAnimator.start();
        } else if(tag.equals(mFadeOutAnimator.getTag())) {
            mFadeOutAnimator = new FadeOutAnimator(mLayoutPassword,"PASSWORD_FADE_OUT");
            mFadeInAnimator.start();
        } else if(tag.equals(mFadeInAnimator.getTag())) {
            mFadeOutAnimator.start();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_next:
                break;
            case R.id.btn_login_sign_in:
                break;
            default:
                break;
        }
    }
}
