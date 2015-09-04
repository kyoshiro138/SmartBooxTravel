package com.smartboox.travel.appimplementation.dialog;

import android.content.Context;

import com.smartboox.travel.core.dialog.Dialog;
import com.smartboox.travel.core.dialog.OnDialogButtonClickListener;
import com.smartboox.travel.core.dialog.alert.ConfirmDialog;
import com.smartboox.travel.core.dialog.alert.ConfirmTitleDialog;
import com.smartboox.travel.core.dialog.alert.WarningDialog;
import com.smartboox.travel.core.dialog.alert.WarningTitleDialog;

public class AppDialogBuilder {
    public static final String DIALOG_GUEST_LOGIN = "DIALOG_GUEST_LOGIN";

    private Dialog mCurrentActiveDialog = null;
    private Context mContext;

    public AppDialogBuilder(Context context) {
        mContext = context;
    }

    public Dialog buildWarningDialog(String tag, String message, OnDialogButtonClickListener listener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            WarningDialog dialog = (WarningDialog) mCurrentActiveDialog;
            dialog.setMessage(message);
            dialog.setOnButtonClickListener(listener);

            return dialog;
        } else {
            WarningDialog dialog = new WarningDialog(mContext);
            dialog.setTag(tag);
            dialog.setMessage(message);
            dialog.setOnButtonClickListener(listener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public Dialog buildWarningDialog(String tag, String title, String message, OnDialogButtonClickListener listener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            WarningTitleDialog dialog = (WarningTitleDialog) mCurrentActiveDialog;
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.setOnButtonClickListener(listener);

            return dialog;
        } else {
            WarningTitleDialog dialog = new WarningTitleDialog(mContext);
            dialog.setTag(tag);
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.setOnButtonClickListener(listener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public Dialog buildConfirmDialog(String tag, String message, OnDialogButtonClickListener listener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            ConfirmDialog dialog = (ConfirmDialog) mCurrentActiveDialog;
            dialog.setMessage(message);
            dialog.setOnButtonClickListener(listener);

            return dialog;
        } else {
            ConfirmDialog dialog = new ConfirmDialog(mContext);
            dialog.setTag(tag);
            dialog.setMessage(message);
            dialog.setOnButtonClickListener(listener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public Dialog buildConfirmDialog(String tag, String title, String message, OnDialogButtonClickListener listener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            ConfirmTitleDialog dialog = (ConfirmTitleDialog) mCurrentActiveDialog;
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.setOnButtonClickListener(listener);

            return dialog;
        } else {
            ConfirmTitleDialog dialog = new ConfirmTitleDialog(mContext);
            dialog.setTag(tag);
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.setOnButtonClickListener(listener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }
}
