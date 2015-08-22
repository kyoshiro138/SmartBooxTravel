package com.smartboox.travel.app.login;

import android.view.View;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.core.view.textfield.SingleLineTextField;

public class LoginFragment extends AppFragment {
    private SingleLineTextField mTextFieldUsername;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    protected void bindView(View rootView) {
        mTextFieldUsername = (SingleLineTextField) rootView.findViewById(R.id.et_login_username);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mTextFieldUsername.requestFocus();
    }
}
