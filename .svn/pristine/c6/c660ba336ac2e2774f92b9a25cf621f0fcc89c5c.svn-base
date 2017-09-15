package com.yuanye.njdt.presenter.engine;

import android.content.Context;
import android.text.TextUtils;

import com.millet.androidlib.EngineBase.EngineBase;
import com.millet.androidlib.Utils.LogUtils;
import com.millet.androidlib.Utils.MD5;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.constants.GlobalVariables;
import com.yuanye.njdt.data.apidata.ChangePassReq;
import com.yuanye.njdt.presenter.enginedelegate.ChangePassEngineDelegate;
import com.yuanye.njdt.presenter.service.IReqResultCallBack;
import com.yuanye.njdt.presenter.service.ServiceManager;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class ChangePassEngine<T extends ChangePassEngineDelegate> extends EngineBase<T> {

    public ChangePassEngine(Context _context) {
        super(_context);
    }

    public ChangePassEngine(Context _context, T _t) {
        super(_context, _t);
    }

    public void changePassWord(String _newPassWord) {
        MD5 _md5 = new MD5();
        try {
            String _passWord = _md5.MD5Encode(_newPassWord);
            if (TextUtils.isEmpty(_passWord)) return;
            int _uid = GlobalVariables.userInfo.getuId();
            if (0 == _uid) return;
            ChangePassReq _changePassReq = new ChangePassReq(_passWord, _uid);
            ServiceManager.getInstance().getIMyService().reqServer(_changePassReq, ApiConfig.ediPass_actionName, new IReqResultCallBack() {
                @Override
                public void onResultSuccess(String _result, String _resultMessage) {
                    notifyChangePassSuccess(_resultMessage);
                }

                @Override
                public void onResultFailure(String _result) {
                    notifyChangePassFailure(_result);
                }
            });
        } catch (NoSuchAlgorithmException _e) {
            _e.printStackTrace();
        }
    }

    public void notifyChangePassSuccess(final String _resultMessage) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ChangePassEngineDelegate _changePassEngineDelegate = getDelegate();
                    if (null != _changePassEngineDelegate) {
                        _changePassEngineDelegate.changePassOnSuccess(_resultMessage);
                    }
                }
            });
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

    public void notifyChangePassFailure(final String _string) {
        try {
            if (!TextUtils.isEmpty(_string)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ChangePassEngineDelegate _changePassEngineDelegate = getDelegate();
                        if (null != _changePassEngineDelegate) {
                            _changePassEngineDelegate.changePassOnError(_string);
                        }
                    }
                });
            }
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

}
