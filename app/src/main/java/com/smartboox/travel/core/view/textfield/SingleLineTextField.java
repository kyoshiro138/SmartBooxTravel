package com.smartboox.travel.core.view.textfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smartboox.travel.R;
import com.smartboox.travel.core.view.base.BaseRelativeLayout;
import com.smartboox.travel.utils.FontUtil;

public class SingleLineTextField extends BaseRelativeLayout implements View.OnFocusChangeListener, TextWatcher {
    private static final String FONT_PATH = "fonts/roboto_regular.ttf";
    private static final float INPUT_FONT_SIZE = 16f;
    private static final float SUB_FONT_SIZE = 12f; // Error and helper font size

    private EditText mEdtInput;
    private View mDivider;
    private TextView mTvError;
    private TextView mTvHelper;
    private boolean mErrorEnabled = false;
    private boolean mHelperEnabled = false;

    private OnTextChangedListener mTextChangedListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.text_field_single_line;
    }

    public SingleLineTextField(Context context) {
        super(context);
    }

    public SingleLineTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
    }

    public SingleLineTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
    }

    @Override
    protected void initLayout(Context context) {
        super.initLayout(context);

        mEdtInput = (EditText) findViewById(R.id.text_field_input);
        mDivider = findViewById(R.id.text_field_divider);
        mTvError = (TextView) findViewById(R.id.text_field_error);
        mTvHelper = (TextView) findViewById(R.id.text_field_helper);

        mEdtInput.setOnFocusChangeListener(this);
        mEdtInput.addTextChangedListener(this);

        mEdtInput.setTypeface(FontUtil.loadFont(context, FONT_PATH));
        mTvError.setTypeface(FontUtil.loadFont(context, FONT_PATH));
        mTvHelper.setTypeface(FontUtil.loadFont(context, FONT_PATH));

        mEdtInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, INPUT_FONT_SIZE);
        mTvError.setTextSize(TypedValue.COMPLEX_UNIT_SP, SUB_FONT_SIZE);
        mTvHelper.setTextSize(TypedValue.COMPLEX_UNIT_SP, SUB_FONT_SIZE);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.SingleLineTextField);

        String helperText = attrsArray.getString(R.styleable.SingleLineTextField_helper_text);
        boolean errorEnabled = attrsArray.getBoolean(R.styleable.SingleLineTextField_error_enabled, false);

        attrsArray.recycle();

        if (helperText != null) {
            mTvHelper.setVisibility(VISIBLE);
            mTvHelper.setText(helperText);
            mHelperEnabled = true;
        }

        if (errorEnabled) {
            mTvError.setVisibility(INVISIBLE);
            mErrorEnabled = true;
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

    public void showError(int errorId) {
        String error = getResources().getString(errorId);
        showError(error);
    }

    public void showError(String error) {
        if (mErrorEnabled && error != null && !error.equals("")) {
            if (mHelperEnabled) {
                mTvHelper.setVisibility(INVISIBLE);
            }
            mTvError.setText(error);
        } else {
            hideError();
        }
    }

    public void hideError() {
        if (mHelperEnabled) {
            mTvHelper.setVisibility(VISIBLE);
        }

        mTvError.setText("");
        mTvError.setVisibility(INVISIBLE);
    }

    public void setOnTextChangedListener(OnTextChangedListener listener) {
        mTextChangedListener = listener;
    }

    public Editable getText() {
        return mEdtInput.getText();
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mTextChangedListener != null) {
            mTextChangedListener.onTextChanged(this);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
