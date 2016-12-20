package com.geebit.app1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by DEll on 2016-12-19.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(initView());


        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initoView();
        initData();
    }
    // 初始化控件, 必须由子类实现
    protected abstract void initoView();
    // 初始化数据, 必须由子类实现
    protected abstract void initData();

    // 初始化布局, 必须由子类实现
    public abstract View initView();
}