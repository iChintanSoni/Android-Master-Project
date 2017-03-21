package com.chintansoni.android.masterproject.activity;

import android.os.Handler;

import com.chintansoni.android.masterproject.R;
import com.chintansoni.android.masterproject.base.BaseAppCompatActivity;
import com.chintansoni.android.masterproject.util.SharedPreferencesUtils;

public class SplashActivity extends BaseAppCompatActivity {

    private final int SPLASH_TIMEOUT = 1000;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            launchActivity(isUserLoggedIn() ? HomeActivity.getActivityIntent(SplashActivity.this) : LoginActivity.getActivityIntent(SplashActivity.this), true);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, SPLASH_TIMEOUT);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    private boolean isUserLoggedIn() {
        return getBoolean(SharedPreferencesUtils.PREFERENCE_IS_USER_LOGGED_IN);
    }
}
