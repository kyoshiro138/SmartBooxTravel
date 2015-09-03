package com.smartboox.travel.core.dialog.alert;

import android.content.Context;
import android.view.View;

import com.smartboox.travel.R;

public class WarningDialog extends BaseCustomAlertDialog {
    @Override
    protected int getDialogLayoutResource() {
        return R.layout.dialog_warning;
    }

    public WarningDialog(Context context) {
        super(context);
    }

    public WarningDialog(Context context, int theme) {
        super(context, theme);
    }

    public WarningDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void initDialog(Context context) {
        super.initDialog(context);
        setButton(BUTTON_POSITIVE, "OK");
    }

    @Override
    protected void bindDialogView(View rootView) {
        bindMessage(rootView, R.id.dialog_message_text);
        bindButton(rootView, BUTTON_POSITIVE, R.id.dialog_positive_button);
    }


}
