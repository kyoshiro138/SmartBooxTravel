package com.smartboox.travel.core.dialog.alert;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.smartboox.travel.R;

public class ConfirmDialog extends BaseCustomAlertDialog {
    @Override
    protected int getDialogLayoutResource() {
        return R.layout.dialog_confirm;
    }

    public ConfirmDialog(Context context) {
        super(context);
    }

    public ConfirmDialog(Context context, int theme) {
        super(context, theme);
    }

    public ConfirmDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void bindDialogView(View rootView) {
        bindMessage(rootView, R.id.dialog_message_text);
        bindButton(rootView, DialogInterface.BUTTON_POSITIVE, R.id.dialog_positive_button);
        bindButton(rootView, DialogInterface.BUTTON_NEUTRAL, R.id.dialog_neutral_button);
        bindButton(rootView, DialogInterface.BUTTON_NEGATIVE, R.id.dialog_negative_button);
    }

    @Override
    protected void initDialog(Context context) {
        super.initDialog(context);

        getButton(BUTTON_POSITIVE).setText("OK");
        getButton(BUTTON_NEGATIVE).setText("CANCEL");

        getButton(BUTTON_NEGATIVE).setVisibility(View.VISIBLE);
    }
}
