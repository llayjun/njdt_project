package com.yuanye.njdt.ui.baseui;


import android.os.Bundle;

import com.millet.androidlib.Base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public abstract class BaseModuleActivity extends BaseActivity {

    protected void initView(Bundle savedInstanceState) {
        setContentView(getViewId());
        ButterKnife.bind(this);
        initViews(savedInstanceState);
    }

    protected abstract int getViewId();

    protected abstract void initViews(Bundle savedInstanceState);

}
