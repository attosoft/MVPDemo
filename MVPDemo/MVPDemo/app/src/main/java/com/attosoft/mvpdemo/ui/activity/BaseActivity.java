package com.attosoft.mvpdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by andy on 16/1/24.
 */
public abstract class BaseActivity extends Activity {

    protected abstract int getLayoutResID();

    protected abstract void findViewByResourceId();

    protected abstract void setListener();

    protected abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getLayoutResID();

        setContentView(layoutId);
        findViewByResourceId();
        initView();
        setListener();
    }

    protected <T extends View> T findView(int id) {
        T view = (T) findViewById(id);
        return view;
    }

}
