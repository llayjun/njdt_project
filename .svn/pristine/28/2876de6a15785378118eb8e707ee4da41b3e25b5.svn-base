package com.yuanye.njdt.ui.baseui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.millet.androidlib.Base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public abstract class BaseModuleFragment extends BaseFragment {

    @Override
    protected void initView(ViewGroup container, Bundle savedInstanceState, View _view) {
        ButterKnife.bind(this, _view);
        initViews(container, savedInstanceState, _view);
    }

    protected abstract void initViews(ViewGroup container, Bundle savedInstanceState, View _view);

}
