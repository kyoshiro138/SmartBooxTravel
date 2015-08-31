package com.smartboox.travel.app.login;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.smartboox.travel.R;
import com.smartboox.travel.app.home.HomeFragment;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.UserManager;
import com.smartboox.travel.appimplementation.service.AppResponseObject;
import com.smartboox.travel.appimplementation.service.response.GetBasicInfoResponseObject;
import com.smartboox.travel.core.animation.BaseAnimator;
import com.smartboox.travel.core.animation.FadeInAnimator;
import com.smartboox.travel.core.animation.FadeOutAnimator;
import com.smartboox.travel.core.animation.OnAnimationBeginListener;
import com.smartboox.travel.core.animation.OnAnimationFinishedListener;
import com.smartboox.travel.core.animation.ResizeAnimator;
import com.smartboox.travel.core.animation.ZoomOutAnimator;
import com.smartboox.travel.core.service.client.OnServiceResponseListener;
import com.smartboox.travel.core.view.textfield.OnTextChangedListener;
import com.smartboox.travel.core.view.textfield.SingleLineTextField;

public class LoginFragment extends AppFragment
        implements View.OnClickListener,
        OnAnimationFinishedListener,
        OnAnimationBeginListener,
        OnTextChangedListener, OnServiceResponseListener<AppResponseObject> {
    private SingleLineTextField mTextFieldUsername;
    private SingleLineTextField mTextFieldPassword;
    private View mLayoutLogin;
    private View mLayoutUsername;
    private View mLayoutPassword;
    private ImageView mImgViewAvatar;
    private TextView mTextViewUsername;

    private ZoomOutAnimator mZoomAnimator;
    private FadeInAnimator mFadeInAnimator;
    private FadeOutAnimator mFadeOutAnimator;
    private FadeOutAnimator mAvatarFadeOutAnimator;
    private ResizeAnimator mResizeAnimator;

    private UserManager mManager;

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
        mTextViewUsername = (TextView) rootView.findViewById(R.id.tv_login_username);
        mTextFieldUsername = (SingleLineTextField) rootView.findViewById(R.id.tf_login_username);
        mTextFieldPassword = (SingleLineTextField) rootView.findViewById(R.id.tf_login_password);

        mTextFieldUsername.setOnTextChangedListener(this);
        mTextFieldPassword.setOnTextChangedListener(this);

        rootView.findViewById(R.id.btn_login_next).setOnClickListener(this);
        rootView.findViewById(R.id.btn_login_sign_in).setOnClickListener(this);
        rootView.findViewById(R.id.btn_login_back).setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        mTextFieldUsername.setHint(R.string.s_screen_login_username);
        mTextFieldPassword.setHint(R.string.s_screen_login_password);

        mManager = new UserManager(getActivity());
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
                String username = mTextFieldUsername.getText().toString();
                if (!username.equals("")) {
                    // TODO: Call get user basic info service
//                    User user = mManager.getUserBasicInfo(username);
//                    if (user != null) {
//                        logDebug("USER");
//                        mTextViewUsername.setText(username);
//                        mTextFieldPassword.setText("");
//                        showPasswordLayout();
//                    } else {
//                        // TODO: Show dialog
//                        logDebug("USERNAME NOT EXISTED");
//                    }
                    mManager.startGetBasicInfoService(username, this);
                } else {
                    mTextFieldUsername.showError("Username can not be empty");
                }
                break;
            case R.id.btn_login_sign_in:
                String password = mTextFieldPassword.getText().toString();
                if (!password.equals("")) {
                    // TODO: Call authenticate service
                    username = mTextViewUsername.getText().toString();
                    User user = mManager.authenticate(username, password);
                    if (user != null) {
                        getNavigator().navigateToFirstLevelFragment(new HomeFragment(), null);
                    } else {
                        // TODO: Show wrong password dialog
                        logDebug("WRONG PASSWORD");
                        showUsernameLayout();
                    }
                } else {
                    mTextFieldPassword.showError("Password can not be empty");
                }
                break;
            case R.id.btn_login_back:
                showUsernameLayout();
                break;
            default:
                break;
        }
    }

    private void showUsernameLayout() {
        int heightChange = getResources().getDimensionPixelSize(R.dimen.d_login_layout_username_height) - getResources().getDimensionPixelSize(R.dimen.d_login_layout_password_height);
        mResizeAnimator.setAnimationView(mLayoutLogin);
        mResizeAnimator.setSizeChange(0, heightChange);
        mResizeAnimator.start();

        mFadeInAnimator.setAnimationView(mLayoutPassword);
        mFadeOutAnimator.setAnimationView(mLayoutUsername);
        mFadeInAnimator.start();
    }

    private void showPasswordLayout() {
        int heightChange = getResources().getDimensionPixelSize(R.dimen.d_login_layout_password_height) - getResources().getDimensionPixelSize(R.dimen.d_login_layout_username_height);
        mResizeAnimator.setAnimationView(mLayoutLogin);
        mResizeAnimator.setSizeChange(0, heightChange);
        mResizeAnimator.start();

        mFadeInAnimator.setAnimationView(mLayoutUsername);
        mFadeOutAnimator.setAnimationView(mLayoutPassword);
        mFadeInAnimator.start();
    }

    @Override
    public void onAnimationBegin(BaseAnimator animator, View animationView) {
        String tag = animator.getTag();

        if (tag.equals(mFadeOutAnimator.getTag())) {
            animationView.setVisibility(View.VISIBLE);
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
    public void onTextChanged(View textField) {
        switch (textField.getId()) {
            case R.id.tf_login_username:
                mTextFieldUsername.hideError();
                break;
            case R.id.tf_login_password:
                mTextFieldPassword.hideError();
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponseSuccess(String tag, AppResponseObject response) {
        if (tag.equals(UserManager.SERVICE_GET_BASIC_INFO)) {
            GetBasicInfoResponseObject responseObject = (GetBasicInfoResponseObject) response;
            showToast(responseObject.getResponseData().getUser().getUsername());
        }
    }

    @Override
    public void onResponseFailed(String tag, VolleyError error) {

    }

    @Override
    public void onParseError(String tag, String response) {

    }
}
