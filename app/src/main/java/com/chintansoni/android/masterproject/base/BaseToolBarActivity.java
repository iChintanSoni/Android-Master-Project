package com.chintansoni.android.masterproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.chintansoni.android.masterproject.R2;

import butterknife.BindView;

public abstract class BaseToolBarActivity extends BaseAppCompatActivity {

    protected static final int RESOURCE_NO_MENU = 0;
    @BindView(R2.id.toolbar)
    public Toolbar mToolbar;

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
    }

    public void setToolBar() {
        setSupportActionBar(mToolbar);
    }

    protected abstract int getMenuResource();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuResource() == RESOURCE_NO_MENU)
            return super.onCreateOptionsMenu(menu);
        else {
            getMenuInflater().inflate(getMenuResource(), menu);
            return true;
        }
    }
}