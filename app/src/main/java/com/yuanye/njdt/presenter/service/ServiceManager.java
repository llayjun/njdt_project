package com.yuanye.njdt.presenter.service;

import android.content.Context;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class ServiceManager {

    private static ServiceManager instance;

    private Context mContext;

    private IMyService mIMyService;

    public synchronized static ServiceManager getInstance() {
        if (null == instance) {
            instance = new ServiceManager();
        }
        return instance;
    }

    private ServiceManager() {
        mIMyService = new MyService();
    }

    public IMyService getIMyService() {
        return mIMyService;
    }

    public void initContext(Context _context) {
        mContext = _context;
    }

    public Context getContext() {
        return mContext;
    }

}
