package com.yuanye.njdt.presenter.service;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public interface IReqResultDownFileCallBack {

    void onResultStart();

    void onResultProgress(int _progress);

    void onResultEnd();

    void onResultFailure(String _result);
}
