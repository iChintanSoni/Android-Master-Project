package com.chintansoni.android.masterproject.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class BaseDialogFragment extends AppCompatDialogFragment {

    private BaseAppCompatActivity mBaseAppCompatActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseAppCompatActivity = (BaseAppCompatActivity) context;
    }

    protected abstract int getLayoutResource();

    protected void hideKeyBoard() {
        ((BaseAppCompatActivity) getActivity()).hideKeyboard();
    }

    public void showDialogFragment(AppCompatDialogFragment appCompatDialogFragment) {
        mBaseAppCompatActivity.showDialogFragment(appCompatDialogFragment);
    }

    public void showDialogFragment(BaseDialogFragment targetFragment, DialogFragment appCompatDialogFragment) {
        mBaseAppCompatActivity.showDialogFragment(targetFragment, appCompatDialogFragment, ((BaseAppCompatActivity) getActivity()).DEFAULT_REQUEST_CODE);
    }

    public void launchActivity(Intent intent, int requestCode) {
        mBaseAppCompatActivity.launchActivity(intent, requestCode, false);
    }

    public void showToast(int resourceId) {
        mBaseAppCompatActivity.showToast(resourceId);
    }

    protected void showProgressDialog() {
        mBaseAppCompatActivity.showProgressDialog();
    }

    protected void hideProgressDialog() {
        mBaseAppCompatActivity.hideProgressDialog();
    }

    protected void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    protected void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    public void saveString(String key, String value) {
        mBaseAppCompatActivity.saveString(key, value);
    }

    public void saveInt(String key, int value) {
        mBaseAppCompatActivity.saveInt(key, value);
    }

    public String getString(String key) {
        return mBaseAppCompatActivity.getString(key);
    }

    public int getInt(String key) {
        return mBaseAppCompatActivity.getInt(key);
    }

    public void clearPreference(String key) {
        mBaseAppCompatActivity.clearPreference(key);
    }

    protected void saveBoolean(String key, boolean value) {
        mBaseAppCompatActivity.saveBoolean(key, value);
    }

    protected boolean getBoolean(String key) {
        return mBaseAppCompatActivity.getBoolean(key);
    }

    public void clearPreferences() {
        mBaseAppCompatActivity.clearPreferences();
    }

    protected void startApiService(Intent intent) {
        mBaseAppCompatActivity.startApiService(intent);
    }

    public void showSnackBar(String message) {
        mBaseAppCompatActivity.showSnackBar(message);
    }

    public void showAlertDialog(int title, int message, DialogInterface.OnClickListener onClickListener) {
        mBaseAppCompatActivity.showAlertDialog(title, message, onClickListener);
    }
}
