package com.chintansoni.android.masterproject.util;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.chintansoni.android.masterproject.customview.RobotoCheckBox;
import com.chintansoni.android.masterproject.customview.RobotoTextInputEditText;

public class FormValidationUtils {

    public static void clear(RobotoTextInputEditText... robotoTextInputEditTexts) {
        for (RobotoTextInputEditText robotoTextInputEditText : robotoTextInputEditTexts)
            robotoTextInputEditText.setError(null);
    }

    public static boolean isEmpty(RobotoTextInputEditText robotoTextInputEditText, String message) {
        if (TextUtils.isEmpty(robotoTextInputEditText.getText().toString().trim())) {
            setFocusAndError(robotoTextInputEditText, message);
            return true;
        } else return false;
    }

    public static boolean isEmailValid(RobotoTextInputEditText robotoTextInputEditText, String message) {
        if (Patterns.EMAIL_ADDRESS.matcher(robotoTextInputEditText.getText()).matches()) {
            return true;
        } else {
            setFocusAndError(robotoTextInputEditText, message);
            return false;
        }
    }

    public static boolean isEmailValid(RobotoTextInputEditText robotoTextInputEditText, String domain, String message) {
        if (Patterns.EMAIL_ADDRESS.matcher(robotoTextInputEditText.getText() + domain).matches()) {
            return true;
        } else {
            setFocusAndError(robotoTextInputEditText, message);
            return false;
        }
    }

    public static boolean arePasswordsSame(RobotoTextInputEditText robotoTextInputEditTextSetPassword, RobotoTextInputEditText robotoTextInputEditTextConfirmPassword, String message) {
        if (robotoTextInputEditTextSetPassword.getText().toString().equals(robotoTextInputEditTextConfirmPassword.getText().toString())) {
            return true;
        } else {
            setFocusAndError(robotoTextInputEditTextSetPassword, message);
            setFocusAndError(robotoTextInputEditTextConfirmPassword, message);
            return false;
        }
    }

    public static boolean isMobileValid(RobotoTextInputEditText robotoTextInputEditText, String message) {
        if (Patterns.PHONE.matcher(robotoTextInputEditText.getText().toString()).matches()) {
            return true;
        } else {
            setFocusAndError(robotoTextInputEditText, message);
            return false;
        }
    }

    public static boolean isChecked(Context context, RobotoCheckBox robotoCheckBox, String message) {
        if (robotoCheckBox.isChecked())
            return true;
        else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private static void setFocusAndError(AppCompatEditText appCompatEditText, String error) {
        appCompatEditText.requestFocus();
        appCompatEditText.setError(error);
    }

}
