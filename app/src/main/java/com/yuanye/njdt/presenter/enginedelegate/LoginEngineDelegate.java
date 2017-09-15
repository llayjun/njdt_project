package com.yuanye.njdt.presenter.enginedelegate;

import com.millet.androidlib.EngineBase.EngineBaseDelegate;
import com.yuanye.njdt.data.entity.UserInfoEntity;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public interface LoginEngineDelegate extends EngineBaseDelegate {

    void loginOnSuccess(UserInfoEntity _userInfoEntity);

    void loginOnError(String _string);
}
