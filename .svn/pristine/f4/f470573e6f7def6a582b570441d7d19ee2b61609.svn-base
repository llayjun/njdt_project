package com.yuanye.njdt.presenter.engine;

import android.content.Context;
import android.text.TextUtils;

import com.millet.androidlib.EngineBase.EngineBase;
import com.millet.androidlib.Utils.GsonUtils;
import com.millet.androidlib.Utils.LogUtils;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.data.apidata.EmergencyListReq;
import com.yuanye.njdt.data.entity.EmergencyListEntity;
import com.yuanye.njdt.presenter.enginedelegate.HistoryFragmentEngineDelegate;
import com.yuanye.njdt.presenter.service.IReqResultCallBack;
import com.yuanye.njdt.presenter.service.ServiceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class HistoryFragmentEngine<T extends HistoryFragmentEngineDelegate> extends EngineBase<T> {

    public HistoryFragmentEngine(Context _context) {
        super(_context);
    }

    public HistoryFragmentEngine(Context _context, T _t) {
        super(_context, _t);
    }

    public void getHistoryList(int _index) {
        try {
            EmergencyListReq _emergencyListReq = new EmergencyListReq("", _index, ApiConfig.history_sortDir, ApiConfig.history_status);
            ServiceManager.getInstance().getIMyService().reqServer(_emergencyListReq, ApiConfig.eventList_actionName, new IReqResultCallBack() {
                @Override
                public void onResultSuccess(String _result, String _resultMessage) {
                    if (TextUtils.isEmpty(_result)) {
                        notifyGetListFailure("error");
                    } else {
                        List<EmergencyListEntity> _listEntities = new ArrayList<>();
                        _listEntities = GsonUtils.jsonToList(_result, EmergencyListEntity.class);
                        notifyGetListSuccess(_listEntities);
                    }
                }

                @Override
                public void onResultFailure(String _result) {
                    notifyGetListFailure(_result);
                }
            });
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

    public void notifyGetListSuccess(final List<EmergencyListEntity> _emergencyListEntities) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    HistoryFragmentEngineDelegate _historyFragmentEngineDelegate = getDelegate();
                    if (null != _historyFragmentEngineDelegate) {
                        _historyFragmentEngineDelegate.HistoryListOnSuccess(_emergencyListEntities);
                    }
                }
            });
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

    public void notifyGetListFailure(final String _string) {
        try {
            if (!TextUtils.isEmpty(_string)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HistoryFragmentEngineDelegate _historyFragmentEngineDelegate = getDelegate();
                        if (null != _historyFragmentEngineDelegate) {
                            _historyFragmentEngineDelegate.HistoryListOnError(_string);
                        }
                    }
                });
            }
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

}
