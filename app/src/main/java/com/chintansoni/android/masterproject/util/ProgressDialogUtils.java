package com.chintansoni.android.masterproject.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.chintansoni.android.masterproject.R;

public class ProgressDialogUtils {

    private static ProgressDialogUtils ourInstance = null;
    private ProgressDialog mProgressDialog;
    private boolean mDefaultIndeterminate = true;
    private boolean mDefaultCancelable = false;
    private DialogInterface.OnCancelListener mDefaultOnCancelListener = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {

        }
    };

    private ProgressDialogUtils() {
    }

    public static ProgressDialogUtils getInstance() {
        if (ourInstance == null)
            ourInstance = new ProgressDialogUtils();
        return ourInstance;
    }

    public void show(Context context) {
        show(context, getTitle(context), getMessage(context), mDefaultIndeterminate, mDefaultCancelable, mDefaultOnCancelListener);
    }

    public void show(Context context, String title) {
        show(context, title, getMessage(context));
    }

    public void show(Context context, String title, String message) {
        show(context, title, message, mDefaultIndeterminate, mDefaultCancelable, mDefaultOnCancelListener);
    }

    public void show(Context context, String title, String message, boolean indeterminate, boolean cancelable, DialogInterface.OnCancelListener onCancelListener) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
        } else {
            mProgressDialog = ProgressDialog.show(context, title, message, indeterminate, cancelable, onCancelListener);
        }
    }

    public void hide() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private String getTitle(Context context) {
        return context.getString(R.string.default_progress_dialog_title);
    }

    private String getMessage(Context context) {
        return context.getString(R.string.default_progress_dialog_message);
    }
}
