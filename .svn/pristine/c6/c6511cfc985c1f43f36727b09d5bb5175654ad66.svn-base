package com.yuanye.njdt.presenter.engine;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.millet.androidlib.EngineBase.EngineBase;
import com.millet.androidlib.EngineBase.EngineBaseDelegate;
import com.millet.androidlib.Utils.LogUtils;
import com.millet.androidlib.Utils.MD5;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.constants.GlobalVariables;
import com.yuanye.njdt.data.apidata.UserLoginReq;
import com.yuanye.njdt.data.entity.UserInfoEntity;
import com.yuanye.njdt.presenter.enginedelegate.LoginEngineDelegate;
import com.yuanye.njdt.presenter.service.IReqResultCallBack;
import com.yuanye.njdt.presenter.service.ServiceManager;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class LoginEngine<T extends LoginEngineDelegate> extends EngineBase {

    public LoginEngine(Context _context) {
        super(_context);
    }

    public LoginEngine(Context _context, EngineBaseDelegate _engineBaseDelegate) {
        super(_context, _engineBaseDelegate);
    }

    public void userLogin(String _userName, String _passWord) {
        MD5 _md5 = new MD5();
        try {
            String _newPassWord = _md5.MD5Encode(_passWord);
            if (TextUtils.isEmpty(_userName)) return;
            if (TextUtils.isEmpty(_newPassWord)) return;
            UserLoginReq _userLoginReq = new UserLoginReq(_userName, _newPassWord);
            ServiceManager.getInstance().getIMyService().reqServer(_userLoginReq, ApiConfig.login_actionName, new IReqResultCallBack() {
                @Override
                public void onResultSuccess(String _result, String _resultMessage) {
                    if (TextUtils.isEmpty(_result)) return;
                    UserInfoEntity _userInfoEntity = new Gson().fromJson(_result, UserInfoEntity.class);
                    if (null != _userInfoEntity)
                        GlobalVariables.userInfo = _userInfoEntity;
                    notifyLoginSuccess(_userInfoEntity);
                }

                @Override
                public void onResultFailure(String _result) {
                    notifyLoginFailure(_result);
                }
            });
        } catch (NoSuchAlgorithmException _e) {
            _e.printStackTrace();
        }
    }

    public void notifyLoginSuccess(final UserInfoEntity _userInfoEntity) {
        try {
            if (null != _userInfoEntity) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoginEngineDelegate _loginEngineDelegate = (LoginEngineDelegate) getDelegate();
                        if (null != _loginEngineDelegate) {
                            _loginEngineDelegate.loginOnSuccess(_userInfoEntity);
                        }
                    }
                });
            }
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

    public void notifyLoginFailure(final String _string) {
        try {
            if (!TextUtils.isEmpty(_string)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoginEngineDelegate _loginEngineDelegate = (LoginEngineDelegate) getDelegate();
                        if (null != _loginEngineDelegate) {
                            _loginEngineDelegate.loginOnError(_string);
                        }
                    }
                });
            }
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

}
