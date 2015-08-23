package com.smartboox.travel.core.view.textfield;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.smartboox.travel.R;
import com.smartboox.travel.core.view.base.BaseRelativeLayout;

public class SingleLineTextField extends BaseRelativeLayout implements View.OnFocusChangeListener {
    private EditText mEdtInput;
    private View mDivider;

    @Override
    protected int getLayoutResource() {
        return R.layout.text_field_single_line;
    }

    public SingleLineTextField(Context context) {
        super(context);
    }

    public SingleLineTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleLineTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initLayout(Context context) {
        super.initLayout(context);

        mEdtInput = (EditText) findViewById(R.id.text_field_input);
        mDivider = findViewById(R.id.text_field_divider);

        mEdtInput.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (mEdtInput.getId() == v.getId()) {
            if (mEdtInput.isEnabled()) {
                if (hasFocus) {
                    mDivider.setBackgroundResource(R.drawable.bg_text_field_divider_focused_light);
                } else {
                    mDivider.setBackgroundResource(R.drawable.bg_divider_light);
                }
            } else {
                mDivider.setBackgroundResource(R.drawable.bg_text_field_divider_disabled_light);
            }
        }

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mEdtInput.setEnabled(enabled);

        if (enabled) {
            mDivider.setBackgroundResource(R.drawable.bg_divider_light);
        } else {
            mDivider.setBackgroundResource(R.drawable.bg_text_field_divider_disabled_light);
        }
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        return mEdtInput.requestFocus();
    }

    public void setHint(int resId) {
        mEdtInput.setHint(resId);
    }
}
