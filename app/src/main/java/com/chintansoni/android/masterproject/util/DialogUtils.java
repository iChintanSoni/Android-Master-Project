package com.chintansoni.android.masterproject.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by chint on 9/4/2016.
 */
public class DialogUtils {

    public static void alert(Context context, int title, int message, int buttonTitle, DialogInterface.OnClickListener onPositiveButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton(buttonTitle, onPositiveButtonClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void alert(Context context, String title, String message, int buttonTitle, DialogInterface.OnClickListener onPositiveButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton(buttonTitle, onPositiveButtonClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void confirm(Context context, int title, int message, int positiveButtonTitle, DialogInterface.OnClickListener onPositiveButtonClickListener, int negativeButtonTitle, DialogInterface.OnClickListener onNegativeButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonTitle, onPositiveButtonClickListener);
        builder.setNegativeButton(negativeButtonTitle, onNegativeButtonClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}