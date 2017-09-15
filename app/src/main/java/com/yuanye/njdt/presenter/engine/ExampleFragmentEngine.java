package com.yuanye.njdt.presenter.engine;

import android.content.Context;
import android.text.TextUtils;

import com.millet.androidlib.EngineBase.EngineBase;
import com.millet.androidlib.Utils.GsonUtils;
import com.millet.androidlib.Utils.LogUtils;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.dao.ExampleDao;
import com.yuanye.njdt.dao.PdfDownDao;
import com.yuanye.njdt.data.entity.ExampleEntity;
import com.yuanye.njdt.data.entity.PdfDownEntity;
import com.yuanye.njdt.presenter.enginedelegate.ExampleFragmentEngineDelegate;
import com.yuanye.njdt.presenter.service.IReqResultCallBack;
import com.yuanye.njdt.presenter.service.IReqResultDownFileCallBack;
import com.yuanye.njdt.presenter.service.ServiceManager;
import com.yuanye.njdt.ui.callback.PdfDownStateListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llay on 2017/9/10.
 */

public class ExampleFragmentEngine<T extends ExampleFragmentEngineDelegate> extends EngineBase<T> {

    public ExampleFragmentEngine(Context _context) {
        super(_context);
    }

    public ExampleFragmentEngine(Context _context, T _t) {
        super(_context, _t);
    }

    public void getExampleList() {
        try {
            ServiceManager.getInstance().getIMyService().reqServerWithNoParam(ApiConfig.jdal_actionName, new IReqResultCallBack() {
                @Override
                public void onResultSuccess(String _result, String _resultMessage) {
                    if (TextUtils.isEmpty(_result)) {
                        notifyGetListFailure("error");
                    } else {
                        List<ExampleEntity> _exampleEntities = new ArrayList<>();
                        _exampleEntities = GsonUtils.jsonToList(_result, ExampleEntity.class);
                        if (null == _exampleEntities) {
                            notifyGetListFailure("error");
                            return;
                        }
                        ExampleDao.insertExampleEntityList(_exampleEntities);
                        notifyGetListSuccess(_exampleEntities);
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

    public void notifyGetListSuccess(final List<ExampleEntity> _exampleEntities) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ExampleFragmentEngineDelegate _exampleFragmentEngineDelegate = getDelegate();
                    if (null != _exampleFragmentEngineDelegate) {
                        _exampleFragmentEngineDelegate.exampleListOnSuccess(_exampleEntities);
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
                        ExampleFragmentEngineDelegate _exampleFragmentEngineDelegate = getDelegate();
                        if (null != _exampleFragmentEngineDelegate) {
                            _exampleFragmentEngineDelegate.exampleListOnError(_string);
                        }
                    }
                });
            }
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

    public void planDownPdf(String _url, String _fileDir, final String _pdfName, final PdfDownStateListener _pdfDownStateListener) {
        try {
            ServiceManager.getInstance().getIMyService().reqDownFileWithNoParam(_url, _fileDir, _pdfName, new IReqResultDownFileCallBack() {
                @Override
                public void onResultStart() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            _pdfDownStateListener.onPdfResultStart();
                        }
                    });
                }

                @Override
                public void onResultProgress(final int _progress) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            _pdfDownStateListener.onPdfResultProgress(_progress);
                        }
                    });
                }

                @Override
                public void onResultEnd() {
                    PdfDownEntity _pdfDownEntity = new PdfDownEntity(_pdfName);
                    PdfDownDao.insertPdfEntity(_pdfDownEntity);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    _pdfDownStateListener.onPdfResultEnd();
                                }
                            });
                        }
                    });
                }

                @Override
                public void onResultFailure(final String _result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            _pdfDownStateListener.onPdfResultFailure(_result);
                        }
                    });
                }
            });
        } catch (Exception _e) {
            LogUtils.catchInfo(_e.toString());
        }
    }

}
