package com.chintansoni.android.masterproject.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chintansoni.android.masterproject.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    protected BaseAppCompatActivity mBaseAppCompatActivity;

    private int FRAGMENT_TRANSACTION_ADD = 200;
    private int FRAGMENT_TRANSACTION_REPLACE = 300;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getResourceLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getResourceLayout();

    protected void setTitle(String title) {
        mBaseAppCompatActivity.getSupportActionBar().setTitle(title);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseAppCompatActivity = (BaseAppCompatActivity) context;
    }

    protected void launchActivity(Intent intent) {
        mBaseAppCompatActivity.launchActivity(intent);
    }

    public void launchActivity(Intent intent, boolean finishCurrent) {
        launchActivity(intent, mBaseAppCompatActivity.DEFAULT_REQUEST_CODE, finishCurrent);
    }

    protected void launchActivity(Intent intent, int requestCode, boolean finishCurrent) {
//        mBaseAppCompatActivity.launchActivity(intent, requestCode, finishCurrent);
        startActivityForResult(intent, requestCode);
    }

    public void showSnackBar(String message) {
        mBaseAppCompatActivity.showSnackBar(message);
    }

    public void showDialogFragment(AppCompatDialogFragment appCompatDialogFragment) {
        mBaseAppCompatActivity.showDialogFragment(appCompatDialogFragment);
    }

    public void showDialogFragment(BaseFragment targetFragment, DialogFragment appCompatDialogFragment) {
        mBaseAppCompatActivity.showDialogFragment(targetFragment, appCompatDialogFragment, mBaseAppCompatActivity.DEFAULT_REQUEST_CODE);
    }

    public void showConfirmDialog(int title, int message, int positiveButtonTitle, DialogInterface.OnClickListener onPositiveButtonClickListener, int negativeButtonTitle, DialogInterface.OnClickListener onNegativeButtonClickListener) {
        mBaseAppCompatActivity.showConfirmDialog(title, message, positiveButtonTitle, onPositiveButtonClickListener, negativeButtonTitle, onNegativeButtonClickListener);
    }

    public void clearPreferences() {
        mBaseAppCompatActivity.clearPreferences();
    }

    protected void showProgressDialog() {
        mBaseAppCompatActivity.showProgressDialog();
    }

    protected void hideProgressDialog() {
        mBaseAppCompatActivity.hideProgressDialog();
    }

    protected void saveString(String key, String value) {
        mBaseAppCompatActivity.saveString(key, value);
    }

    protected String getString(String key) {
        return mBaseAppCompatActivity.getString(key);
    }

    protected void saveBoolean(String key, boolean value) {
        mBaseAppCompatActivity.saveBoolean(key, value);
    }

    protected void showToast(String message) {
        mBaseAppCompatActivity.showToast(message);
    }

    //----------------------------- [Start] Sub Fragment Operations ----------------------------------------

    public void addSubFragment(Fragment fragment) {
        addSubFragment(fragment, false);
    }

    public void addSubFragment(Fragment fragment, boolean addToBackStack) {
        loadSubFragment(fragment, FRAGMENT_TRANSACTION_ADD, addToBackStack);
    }

    public void replaceSubFragment(Fragment fragment) {
        replaceSubFragment(fragment, false);
    }

    public void replaceSubFragment(Fragment fragment, boolean addToBackStack) {
        loadSubFragment(fragment, FRAGMENT_TRANSACTION_REPLACE, addToBackStack);
    }

    private void loadSubFragment(Fragment fragment, int transactionType) {
        loadSubFragment(fragment, transactionType, false);
    }

    private void loadSubFragment(Fragment fragment, int transactionType, boolean addToBackStack) {
        mBaseAppCompatActivity.loadFragment(fragment, R.id.frameLayoutSub, transactionType, addToBackStack);
    }

    //----------------------------- [End] Sub Fragment Operations ----------------------------------------

    //----------------------------- [Start] Fragment Operations ----------------------------------------

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

    private void loadFragment(Fragment fragment, int containerId, int transactionType, boolean addToBackStack) {
        mBaseAppCompatActivity.loadFragment(fragment, containerId, transactionType, addToBackStack);
    }

    //----------------------------- [Start] Fragment Operations ----------------------------------------

    protected void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    protected void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    public void logError(Class tag, String message) {
        mBaseAppCompatActivity.logError(tag, message);
    }

    public void logInformation(Class tag, String message) {
        mBaseAppCompatActivity.logInformation(tag, message);
    }

    public void logDebug(Class tag, String message) {
        mBaseAppCompatActivity.logDebug(tag, message);
    }

    public void logWarning(Class tag, String message) {
        mBaseAppCompatActivity.logWarning(tag, message);
    }

    public void logVerbose(Class tag, String message) {
        mBaseAppCompatActivity.logVerbose(tag, message);
    }

    public void logWTF(Class tag, String message) {
        mBaseAppCompatActivity.logWTF(tag, message);
    }

    protected void startApiService(Intent intent) {
        mBaseAppCompatActivity.startApiService(intent);
    }

    protected void finishWithResultOk() {
        mBaseAppCompatActivity.finishWithResultOk();
    }

    public void showAlertDialog(int title, int message, DialogInterface.OnClickListener onClickListener) {
        mBaseAppCompatActivity.showAlertDialog(title, message, onClickListener);
    }

    public void showAlertDialog(String title, String message, DialogInterface.OnClickListener onClickListener) {
        mBaseAppCompatActivity.showAlertDialog(title, message, onClickListener);
    }
}
