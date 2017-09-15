package com.yuanye.njdt.presenter.enginedelegate;

import com.millet.androidlib.EngineBase.EngineBaseDelegate;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public interface ChangePassEngineDelegate extends EngineBaseDelegate {

    void changePassOnSuccess(String _resultMessage);

    void changePassOnError(String _string);
}
