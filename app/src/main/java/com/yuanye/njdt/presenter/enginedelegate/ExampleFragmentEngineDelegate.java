package com.yuanye.njdt.presenter.enginedelegate;

import com.millet.androidlib.EngineBase.EngineBaseDelegate;
import com.yuanye.njdt.data.entity.ExampleEntity;

import java.util.List;

/**
 * Created by llay on 2017/9/10.
 */

public interface ExampleFragmentEngineDelegate extends EngineBaseDelegate {

    void exampleListOnSuccess(final List<ExampleEntity> _exampleEntityList);

    void exampleListOnError(String _string);
}
