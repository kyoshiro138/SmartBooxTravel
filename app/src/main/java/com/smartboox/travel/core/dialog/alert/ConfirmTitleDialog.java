package com.smartboox.travel.core.dialog.alert;

import android.content.Context;
import android.view.View;

import com.smartboox.travel.R;

public class ConfirmTitleDialog extends ConfirmDialog {
    @Override
    protected int getDialogLayoutResource() {
        return R.layout.dialog_confirm_title;
    }

    public ConfirmTitleDialog(Context context) {
        super(context);
    }

    public ConfirmTitleDialog(Context context, int theme) {
        super(context, theme);
    }

    public ConfirmTitleDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void bindDialogView(View rootView) {
        super.bindDialogView(rootView);
        bindTitle(rootView, R.id.dialog_title_text);
    }
}
