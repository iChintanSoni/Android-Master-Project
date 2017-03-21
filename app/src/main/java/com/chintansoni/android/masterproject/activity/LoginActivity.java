package com.chintansoni.android.masterproject.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.chintansoni.android.masterproject.R;
import com.chintansoni.android.masterproject.R2;
import com.chintansoni.android.masterproject.base.BaseAppCompatActivity;
import com.chintansoni.android.masterproject.customview.RobotoTextInputEditText;
import com.chintansoni.android.masterproject.service.BaseApi;
import com.chintansoni.android.masterproject.service.api.LoginApi;
import com.chintansoni.android.masterproject.util.FormValidationUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseAppCompatActivity {

    @BindView(R2.id.tiet_login_email)
    RobotoTextInputEditText mRobotoTextInputEditTextEmail;

    @BindView(R2.id.tiet_login_password)
    RobotoTextInputEditText mRobotoTextInputEditTextPassword;

    public static Intent getActivityIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @OnClick(R2.id.btn_login_login)
    void onLoginClick() {
        if (FormValidationUtils.isEmpty(mRobotoTextInputEditTextEmail, "Please enter email"))
            return;
        if (!FormValidationUtils.isEmailValid(mRobotoTextInputEditTextEmail, "Please enter valid email"))
            return;
        if (FormValidationUtils.isEmpty(mRobotoTextInputEditTextPassword, "Please enter password"))
            return;

        launchActivity(HomeActivity.getActivityIntent(this));
//        startApiService(LoginApi.getServiceIntent(this, mRobotoTextInputEditTextEmail.getText().toString().trim(), mRobotoTextInputEditTextPassword.getText().toString()));
    }

    @OnClick(R2.id.btn_login_register)
    void onRegisterClick() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public void onResume() {
        super.onResume();
        registerEventBus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unRegisterEventBus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseApi.EventBusMessage eventBusMessage) {
        if (eventBusMessage.event.equals(BaseApi.EventBusMessage.EVENT_SHOW_PROGRESS_DIALOG))
            showProgressDialog();
        else if (eventBusMessage.event.equals(BaseApi.EventBusMessage.EVENT_HIDE_PROGRESS_DIALOG))
            hideProgressDialog();
        else {
            if (eventBusMessage.object instanceof LoginApi.ApiResponse)
                parseSuccessResponse((LoginApi.ApiResponse) eventBusMessage.object);
            else if (eventBusMessage.object instanceof BaseApi.DefaultErrorResponse) {
                parseErrorResponse((BaseApi.DefaultErrorResponse) eventBusMessage.object);
            }
        }
    }

    private void parseSuccessResponse(LoginApi.ApiResponse apiResponse) {
        launchActivity(HomeActivity.getActivityIntent(this), true);
    }

    private void parseErrorResponse(BaseApi.DefaultErrorResponse defaultErrorResponse) {
        showSnackBar(defaultErrorResponse.getMessage());
    }
}
