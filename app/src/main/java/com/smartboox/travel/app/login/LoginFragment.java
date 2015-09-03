package com.smartboox.travel.app.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.smartboox.travel.R;
import com.smartboox.travel.app.home.HomeFragment;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.UserManager;
import com.smartboox.travel.appimplementation.service.AppResponseObject;
import com.smartboox.travel.appimplementation.service.response.AuthenticateResponseObject;
import com.smartboox.travel.appimplementation.service.response.GetBasicInfoResponseObject;
import com.smartboox.travel.core.animation.BaseAnimator;
import com.smartboox.travel.core.animation.FadeInAnimator;
import com.smartboox.travel.core.animation.FadeOutAnimator;
import com.smartboox.travel.core.animation.OnAnimationBeginListener;
import com.smartboox.travel.core.animation.OnAnimationFinishedListener;
import com.smartboox.travel.core.animation.ResizeAnimator;
import com.smartboox.travel.core.animation.ZoomOutAnimator;
import com.smartboox.travel.core.dialog.alert.WarningDialog;
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
    private TextView mTextViewCreateAccount;

    private ZoomOutAnimator mZoomAnimator;
    private FadeInAnimator mFadeInAnimator;
    private FadeOutAnimator mFadeOutAnimator;
    private FadeOutAnimator mAvatarFadeOutAnimator;
    private FadeOutAnimator mRegisterFadeOutAnimator;
    private FadeInAnimator mRegisterFadeInAnimator;
    private ResizeAnimator mResizeAnimator;

    private UserManager mManager;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity.getToolbar().setToolbarVisibility(View.GONE);
    }

    @Override
    protected void bindView(final View rootView) {
        mLayoutLogin = rootView.findViewById(R.id.layout_login);
        mLayoutUsername = rootView.findViewById(R.id.layout_login_username);
        mLayoutPassword = rootView.findViewById(R.id.layout_login_password);

        mImgViewAvatar = (ImageView) rootView.findViewById(R.id.img_login_avatar);
        mTextViewUsername = (TextView) rootView.findViewById(R.id.tv_login_username);
        mTextViewCreateAccount = (TextView) rootView.findViewById(R.id.tv_login_create_account);
        mTextFieldUsername = (SingleLineTextField) rootView.findViewById(R.id.tf_login_username);
        mTextFieldPassword = (SingleLineTextField) rootView.findViewById(R.id.tf_login_password);

        mTextFieldUsername.setOnTextChangedListener(this);
        mTextFieldPassword.setOnTextChangedListener(this);

        rootView.findViewById(R.id.btn_login_next).setOnClickListener(this);
        rootView.findViewById(R.id.btn_login_sign_in).setOnClickListener(this);
        rootView.findViewById(R.id.btn_login_back).setOnClickListener(this);
        mTextViewCreateAccount.setOnClickListener(this);
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
        if (mRegisterFadeOutAnimator == null) {
            mRegisterFadeOutAnimator = new FadeOutAnimator("REGISTER_FADE_OUT");
            mRegisterFadeOutAnimator.setAnimationView(mTextViewCreateAccount);
        }
        if (mRegisterFadeInAnimator == null) {
            mRegisterFadeInAnimator = new FadeInAnimator("REGISTER_FADE_IN");
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
                    mManager.startGetBasicInfoService(username, this);
                } else {
                    mTextFieldUsername.showError("Username can not be empty");
                }
                break;
            case R.id.btn_login_sign_in:
                String password = mTextFieldPassword.getText().toString();
                if (!password.equals("")) {
                    username = mTextViewUsername.getText().toString();
                    mManager.authenticate(username, password, this);
                } else {
                    mTextFieldPassword.showError("Password can not be empty");
                }
                break;
            case R.id.btn_login_back:
                showUsernameLayout();
                break;
            case R.id.tv_login_create_account:
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
        mRegisterFadeOutAnimator.setAnimationView(mTextViewCreateAccount);
        mFadeInAnimator.start();
        mRegisterFadeOutAnimator.start();
    }

    private void showPasswordLayout() {
        int heightChange = getResources().getDimensionPixelSize(R.dimen.d_login_layout_password_height) - getResources().getDimensionPixelSize(R.dimen.d_login_layout_username_height);
        mResizeAnimator.setAnimationView(mLayoutLogin);
        mResizeAnimator.setSizeChange(0, heightChange);
        mResizeAnimator.start();

        mFadeOutAnimator.setAnimationView(mLayoutPassword);
        mFadeInAnimator.setAnimationView(mLayoutUsername);
        mRegisterFadeInAnimator.setAnimationView(mTextViewCreateAccount);
        mFadeInAnimator.start();
        mRegisterFadeInAnimator.start();
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
            mRegisterFadeOutAnimator.start();
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
        switch (tag) {
            case UserManager.SERVICE_GET_BASIC_INFO:
                onGetBasicInfoResponse((GetBasicInfoResponseObject) response);
                break;
            case UserManager.SERVICE_AUTHENTICATION:
                onAuthenticationResponse((AuthenticateResponseObject) response);
                break;
            default:
                break;
        }
    }


    @Override
    public void onResponseFailed(String tag, VolleyError error) {

    }

    @Override
    public void onParseError(String tag, String response) {

    }

    private void onGetBasicInfoResponse(GetBasicInfoResponseObject responseObject) {
        boolean isSuccess = responseObject.getStatus();
        if (isSuccess) {
            mTextViewUsername.setText(responseObject.getResponseData().getUser().getUsername());
            mTextFieldPassword.setText("");
            showPasswordLayout();
        } else {
            // TODO: Show dialog to login as guest
            int errorCode = responseObject.getResponseCode();
            String message = responseObject.getMessage();
            showToast(String.format("Error %d: %s", errorCode, message));
        }
    }

    private void onAuthenticationResponse(AuthenticateResponseObject responseObject) {
        boolean isSuccess = responseObject.getStatus();
        if (isSuccess) {
            mManager.saveSignIn(responseObject.getResponseData().getUser(), responseObject.getResponseData().getKey());
            getNavigator().navigateToFirstLevelFragment(new HomeFragment(), null);
        } else {
            // TODO: Show dialog wrong password warning
            int errorCode = responseObject.getResponseCode();
            String message = responseObject.getMessage();
            showToast(String.format("Error %d: %s", errorCode, message));
        }
    }
}
