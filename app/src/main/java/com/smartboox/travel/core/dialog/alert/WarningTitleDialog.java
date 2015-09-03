package com.smartboox.travel.core.dialog.alert;

import android.content.Context;
import android.view.View;

import com.smartboox.travel.R;

public class WarningTitleDialog extends WarningDialog {
    @Override
    protected int getDialogLayoutResource() {
        return R.layout.dialog_warning_title;
    }

    public WarningTitleDialog(Context context) {
        super(context);
    }

    public WarningTitleDialog(Context context, int theme) {
        super(context, theme);
    }

    public WarningTitleDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void bindDialogView(View rootView) {
        super.bindDialogView(rootView);
        bindTitle(rootView, R.id.dialog_title_text);
    }
}
