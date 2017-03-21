package com.chintansoni.android.masterproject.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.chintansoni.android.masterproject.R;
import com.chintansoni.android.masterproject.activity.LoginActivity;
import com.chintansoni.android.masterproject.util.DialogUtils;
import com.chintansoni.android.masterproject.util.LogUtils;
import com.chintansoni.android.masterproject.util.ProgressDialogUtils;
import com.chintansoni.android.masterproject.util.SharedPreferencesUtils;
import com.chintansoni.android.masterproject.util.SnackBarUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chintan Soni - Senior Software Engineer (Android).
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    public int DEFAULT_REQUEST_CODE = 100;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;

    private int FRAGMENT_TRANSACTION_ADD = 200;
    private int FRAGMENT_TRANSACTION_REPLACE = 300;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutResource();

    public void showSnackBar(int message) {
        SnackBarUtils.show(mCoordinatorLayout, message);
    }

    public void showSnackBar(String message) {
        SnackBarUtils.show(mCoordinatorLayout, message);
    }

    public void showSnackBar(int message, int duration) {
        SnackBarUtils.show(mCoordinatorLayout, message, duration);
    }

    public void showSnackBar(String message, int duration) {
        SnackBarUtils.show(mCoordinatorLayout, message, duration);
    }

    public void showProgressDialog() {
        ProgressDialogUtils.getInstance().show(this);
    }

    public void showProgressDialog(String title) {
        ProgressDialogUtils.getInstance().show(this, title);
    }

    public void showProgressDialog(String title, String message) {
        ProgressDialogUtils.getInstance().show(this, title, message);
    }

    public void hideProgressDialog() {
        ProgressDialogUtils.getInstance().hide();
    }

    public void showAlertDialog(int title, int message, DialogInterface.OnClickListener onPositiveButtonClickListener) {
        DialogUtils.alert(this, title, message, R.string.default_alert_dialog_button_title, onPositiveButtonClickListener);
    }

    public void showAlertDialog(String title, String message, DialogInterface.OnClickListener onPositiveButtonClickListener) {
        DialogUtils.alert(this, title, message, R.string.default_alert_dialog_button_title, onPositiveButtonClickListener);
    }

    public void showConfirmDialog(int title, int message, int positiveButtonTitle, DialogInterface.OnClickListener onPositiveButtonClickListener, int negativeButtonTitle, DialogInterface.OnClickListener onNegativeButtonClickListener) {
        DialogUtils.confirm(this, title, message, positiveButtonTitle, onPositiveButtonClickListener, negativeButtonTitle, onNegativeButtonClickListener);
    }

    public void logError(Class tag, String message) {
        LogUtils.error(tag, message);
    }

    public void logInformation(Class tag, String message) {
        LogUtils.information(tag, message);
    }

    public void logDebug(Class tag, String message) {
        LogUtils.debug(tag, message);
    }

    public void logWarning(Class tag, String message) {
        LogUtils.warning(tag, message);
    }

    public void logVerbose(Class tag, String message) {
        LogUtils.verbose(tag, message);
    }

    public void logWTF(Class tag, String message) {
        LogUtils.wtf(tag, message);
    }

    public void launchActivity(Intent intent) {
        launchActivity(intent, false);
    }

    public void launchActivity(Intent intent, boolean finishCurrent) {
        launchActivity(intent, DEFAULT_REQUEST_CODE, finishCurrent);
    }

    public void launchActivity(Intent intent, int requestCode, boolean finishCurrent) {
        if (requestCode != DEFAULT_REQUEST_CODE) {
            startActivityForResult(intent, requestCode);
        } else {
            if (finishCurrent) {
                finish();
            }
            startActivity(intent);
        }
    }

    public void addFragment(Fragment fragment) {
        addFragment(fragment, false);
    }

    public void addFragment(Fragment fragment, boolean addToBackStack) {
        loadFragment(fragment, FRAGMENT_TRANSACTION_ADD, addToBackStack);
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        loadFragment(fragment, FRAGMENT_TRANSACTION_REPLACE, addToBackStack);
    }

    private void loadFragment(Fragment fragment, int transactionType) {
        loadFragment(fragment, transactionType, false);
    }

    private void loadFragment(Fragment fragment, int transactionType, boolean addToBackStack) {
        loadFragment(fragment, R.id.frameLayout, transactionType, addToBackStack);
    }

    void loadFragment(Fragment fragment, int containerId, int transactionType, boolean addToBackStack) {
        String fragmentName = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName);
        }
        if (transactionType == FRAGMENT_TRANSACTION_ADD) {
            fragmentTransaction.add(containerId, fragment, fragmentName);
        } else {
            fragmentTransaction.replace(containerId, fragment, fragmentName);
        }
        fragmentTransaction.commit();
    }

    public void showDialogFragment(AppCompatDialogFragment appCompatDialogFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(appCompatDialogFragment, appCompatDialogFragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void showDialogFragment(BaseFragment targetFragment, DialogFragment appCompatDialogFragment, int requestCode) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        appCompatDialogFragment.setTargetFragment(targetFragment, requestCode);
        fragmentTransaction.add(appCompatDialogFragment, appCompatDialogFragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void showDialogFragment(BaseDialogFragment targetFragment, DialogFragment appCompatDialogFragment, int requestCode) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        appCompatDialogFragment.setTargetFragment(targetFragment, requestCode);
        fragmentTransaction.add(appCompatDialogFragment, appCompatDialogFragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showToast(int resourceId) {
        Toast.makeText(this, resourceId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String resource) {
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resourceId, int toastLong) {
        Toast.makeText(this, resourceId, toastLong).show();
    }

    public void showToast(String resource, int toastLong) {
        Toast.makeText(this, resource, toastLong).show();
    }

    public void startApiService(Intent intent) {
        startService(intent);
    }

    public void saveString(String key, String value) {
        SharedPreferencesUtils.getInstance(this).setString(key, value);
    }

    public void saveInt(String key, int value) {
        SharedPreferencesUtils.getInstance(this).setInt(key, value);
    }

    public String getString(String key) {
        return SharedPreferencesUtils.getInstance(this).getString(key);
    }

    public int getInt(String key) {
        return SharedPreferencesUtils.getInstance(this).getInt(key);
    }

    public void clearPreference(String key) {
        SharedPreferencesUtils.getInstance(this).clearKey(key);
    }

    protected void saveBoolean(String key, boolean value) {
        SharedPreferencesUtils.getInstance(this).setBoolean(key, value);
    }

    protected boolean getBoolean(String key) {
        return SharedPreferencesUtils.getInstance(this).getBoolean(key);
    }

    public void clearPreferences() {
        SharedPreferencesUtils.getInstance(this).clearPreferences();
    }

    public void finishWithResultOk() {
        finishWithResultOk(null);
    }

    protected void finishWithResultOk(Intent intent) {
        if (intent == null)
            setResult(RESULT_OK);
        else
            setResult(RESULT_OK, intent);
        finish();
    }

    protected void finishWithResultCancel() {
        finishWithResultCancel(null);
    }

    protected void finishWithResultCancel(Intent intent) {
        if (intent == null)
            setResult(RESULT_CANCELED);
        else
            setResult(RESULT_CANCELED, intent);
        finish();
    }

    protected void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    protected void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    public void logout() {
        clearPreferences();
        Intent intent = LoginActivity.getActivityIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        launchActivity(intent, true);
    }

    protected Fragment findFragment(Class fragment) {
        Fragment fragment1 = getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
        if (fragment1 != null && fragment1.isVisible())
            return fragment1;
        else
            return null;
    }
}
