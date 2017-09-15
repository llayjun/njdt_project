package com.yuanye.njdt.presenter.engine;

import android.content.Context;

import com.millet.androidlib.EngineBase.EngineBase;
import com.yuanye.njdt.presenter.enginedelegate.PublishEventEngineDelegate;

/**
 * Created by Administrator on 2017/9/14 0014.
 */

public class PublishEventEngine<T extends PublishEventEngineDelegate> extends EngineBase<T> {

    public PublishEventEngine(Context _context) {
        super(_context);
    }

    public PublishEventEngine(Context _context, T _t) {
        super(_context, _t);
    }

}
