package com.chintansoni.android.masterproject.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.chintansoni.android.masterproject.R;


public class SnackBarUtils {

    public static void show(View view, int description) {
        show(view, description, Snackbar.LENGTH_SHORT);
    }

    public static void show(View view, int description, int duration) {
        show(view, description, duration, R.string.default_snackbar_action_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public static void show(View view, int description, int duration, int actionTitle, View.OnClickListener onActionClickListener) {
        Snackbar.make(view, description, duration)
                .setAction(actionTitle, onActionClickListener)
                .show();
    }

    public static void show(View view, String description) {
        show(view, description, Snackbar.LENGTH_SHORT);
    }

    public static void show(View view, String description, int duration) {
        show(view, description, duration, R.string.default_snackbar_action_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public static void show(View view, String description, int duration, int actionTitle, View.OnClickListener onActionClickListener) {
        Snackbar.make(view, description, duration)
                .setAction(actionTitle, onActionClickListener)
                .show();
    }
}
