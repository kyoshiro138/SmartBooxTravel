package com.smartboox.travel.app.login;

import android.animation.Animator;
import android.view.View;
import android.widget.ImageView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.core.animation.FadeOutAnimator;
import com.smartboox.travel.core.animation.OnAnimationEndListener;
import com.smartboox.travel.core.animation.ZoomOutAnimator;
import com.smartboox.travel.core.animation.group.FadeOutGroupAnimator;
import com.smartboox.travel.core.view.textfield.SingleLineTextField;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends AppFragment implements OnAnimationEndListener {
    private SingleLineTextField mTextFieldUsername;
    private SingleLineTextField mTextFieldPassword;
    private View mLayoutLogin;
    private View mLayoutUsername;
    private View mLayoutPassword;
    private ImageView mImgViewAvatar;

    private ZoomOutAnimator mZoomAnimator;
    private FadeOutGroupAnimator mFadeOutAnimator;

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
            List<View> viewList = new ArrayList<>();
            viewList.add(mLayoutUsername);
            viewList.add(mImgViewAvatar);
            mFadeOutAnimator = new FadeOutGroupAnimator(viewList);
        }

        mZoomAnimator.start();
    }

    @Override
    public void onAnimationEnded(String tag, Animator animator) {
        mFadeOutAnimator.start();
    }
}
