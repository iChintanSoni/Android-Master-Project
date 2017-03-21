package com.chintansoni.android.masterproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chintansoni.android.masterproject.R;
import com.chintansoni.android.masterproject.base.BaseNavigationDrawerActivity;
import com.chintansoni.android.masterproject.fragment.HomeImportFragment;

public class HomeActivity extends BaseNavigationDrawerActivity {

    public static Intent getActivityIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadDefaultFragment();
    }

    private void loadDefaultFragment() {
        replaceFragment(HomeImportFragment.getFragment());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected int getMenuResource() {
        return RESOURCE_NO_MENU;
    }
}
