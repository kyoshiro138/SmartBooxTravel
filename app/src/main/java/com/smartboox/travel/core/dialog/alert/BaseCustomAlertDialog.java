package com.smartboox.travel.core.dialog.alert;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public abstract class BaseCustomAlertDialog extends BaseAlertDialog implements View.OnClickListener {
    private Button mPositiveButton;
    private Button mNeutralButton;
    private Button mNegativeButton;
    private TextView mTitleText;
    private TextView mMessageText;

    protected abstract int getDialogLayoutResource();

    public BaseCustomAlertDialog(Context context) {
        super(context);
    }

    public BaseCustomAlertDialog(Context context, int theme) {
        super(context, theme);
    }

    public BaseCustomAlertDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void initDialog(Context context) {
        super.initDialog(context);

        if (getDialogLayoutResource() != 0) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = inflater.inflate(getDialogLayoutResource(), null, false);
            setView(rootView);

            bindDialogView(rootView);
        }
    }

    protected abstract void bindDialogView(View rootView);

    protected void bindButton(View rootView, int which, int viewId) {
        if (rootView != null && viewId > 0) {
            switch (which) {
                case BUTTON_POSITIVE:
                    mPositiveButton = (Button) rootView.findViewById(viewId);
                    break;
                case BUTTON_NEUTRAL:
                    mNeutralButton = (Button) rootView.findViewById(viewId);
                    break;
                case BUTTON_NEGATIVE:
                    mNegativeButton = (Button) rootView.findViewById(viewId);
                    break;
                default:
                    break;
            }
        }
    }

    protected void bindTitle(View rootView, int viewId) {
        if (rootView != null && viewId > 0) {
            mTitleText = (TextView) rootView.findViewById(viewId);
        }
    }

    protected void bindMessage(View rootView, int viewId) {
        if (rootView != null && viewId > 0) {
            mMessageText = (TextView) rootView.findViewById(viewId);
        }
    }

    @Override
    public void setButton(int whichButton, CharSequence text) {
        switch (whichButton) {
            case BUTTON_POSITIVE:
                if (mPositiveButton != null) {
                    mPositiveButton.setText(text);
                    mPositiveButton.setOnClickListener(this);
                }
                break;
            case BUTTON_NEUTRAL:
                if (mNeutralButton != null) {
                    mNeutralButton.setText(text);
                    mNeutralButton.setOnClickListener(this);
                }
                break;
            case BUTTON_NEGATIVE:
                if (mNegativeButton != null) {
                    mNegativeButton.setText(text);
                    mNegativeButton.setOnClickListener(this);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setButton(int whichButton, int textId) {
        CharSequence text = mContext.getText(textId);
        setButton(whichButton, text);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mTitleText != null) {
            mTitleText.setText(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        String title = mContext.getString(titleId);
        if (title != null && !title.equals("")) {
            mTitleText.setText(title);
        }
    }

    @Override
    public void setMessage(CharSequence message) {
        if (mMessageText != null) {
            mMessageText.setText(message);
        }
    }

    @Override
    public void onClick(View v) {
        if (mPositiveButton != null && v.getId() == mPositiveButton.getId()) {
            onClick(this, BUTTON_POSITIVE);
        } else if (mNeutralButton != null && v.getId() == mNeutralButton.getId()) {
            onClick(this, BUTTON_NEUTRAL);
        } else if (mNegativeButton != null && v.getId() == mNegativeButton.getId()) {
            onClick(this, BUTTON_NEGATIVE);
        }
    }
}
