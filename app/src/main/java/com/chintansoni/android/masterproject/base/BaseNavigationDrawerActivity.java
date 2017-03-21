package com.chintansoni.android.masterproject.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.chintansoni.android.masterproject.R;
import com.chintansoni.android.masterproject.R2;

import butterknife.BindView;

public abstract class BaseNavigationDrawerActivity extends BaseToolBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R2.id.dl_home)
    protected DrawerLayout mDrawerLayout;

    @BindView(R2.id.nv_home)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                getmToolbar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
