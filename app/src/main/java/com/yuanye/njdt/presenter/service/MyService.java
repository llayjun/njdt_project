package com.yuanye.njdt.presenter.service;

import com.google.gson.Gson;
import com.millet.androidlib.Net.OkHttpCallBack;
import com.millet.androidlib.Net.OkHttpManager;
import com.millet.androidlib.Utils.LogUtils;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.data.entity.ReqResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class MyService implements IMyService {

    @Override
    public void reqServer(Serializable _serializable, String _headerValue, final IReqResultCallBack _iReqResultCallBack) {
        OkHttpManager.getInstance().postAsyncJson(_serializable, ApiConfig.URL, ApiConfig.HEAD, _headerValue, new OkHttpCallBack() {
            @Override
            public void requestFailure(Request _request, IOException _io) {
                _iReqResultCallBack.onResultFailure(_io.getMessage());
            }

            @Override
            public void requestSuccess(Call call, Response _response) throws Exception {
                String _result = _response.body().string();
                ReqResult _reqResult = new Gson().fromJson(_result, ReqResult.class);
                if (ApiConfig.SuccessCode == _reqResult.getCode()) {
                    _iReqResultCallBack.onResultSuccess(_reqResult.getData(), _reqResult.getErrMsg());
                } else {
                    _iReqResultCallBack.onResultFailure(_reqResult.getErrMsg());
                }
            }
        });
    }

    @Override
    public void reqServerWithNoParam(String _headerValue, final IReqResultCallBack _iReqResultCallBack) {
        OkHttpManager.getInstance().postAsyncWithNoParam(ApiConfig.URL, ApiConfig.HEAD, _headerValue, new OkHttpCallBack() {
            @Override
            public void requestFailure(Request _request, IOException _io) {
                _iReqResultCallBack.onResultFailure(_io.getMessage());
            }

            @Override
            public void requestSuccess(Call call, Response _response) throws Exception {
                String _result = _response.body().string();
                ReqResult _reqResult = new Gson().fromJson(_result, ReqResult.class);
                if (ApiConfig.SuccessCode == _reqResult.getCode()) {
                    _iReqResultCallBack.onResultSuccess(_reqResult.getData(), _reqResult.getErrMsg());
                } else {
                    _iReqResultCallBack.onResultFailure(_reqResult.getErrMsg());
                }
            }
        });
    }

    /**
     * @param _url
     * @param _fileDir
     * @param _fileName
     * @param _iReqResultDownFileCallBack
     */
    @Override
    public void reqDownFileWithNoParam(String _url, final String _fileDir, final String _fileName, final IReqResultDownFileCallBack _iReqResultDownFileCallBack) {
        OkHttpManager.getInstance().getAsync(_url, new OkHttpCallBack() {
            @Override
            public void requestFailure(Request _request, IOException _io) {
                _iReqResultDownFileCallBack.onResultFailure(_io.getMessage());
            }

            @Override
            public void requestSuccess(Call call, Response _response) throws Exception {
                if (null == _response) {
                    _iReqResultDownFileCallBack.onResultFailure(_response.message());
                    return;
                }
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = _response.body().contentLength();
                    long current = 0;
                    is = _response.body().byteStream();
                    File _file = new File(_fileDir, _fileName);
                    fos = new FileOutputStream(_file);
                    _iReqResultDownFileCallBack.onResultStart();
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        int progress = (int) (((float) current / total) * 100);
                        _iReqResultDownFileCallBack.onResultProgress(progress);
                    }
                    fos.flush();
                    _iReqResultDownFileCallBack.onResultEnd();
                } catch (IOException e) {
                    LogUtils.catchInfo(e.toString());
                    _iReqResultDownFileCallBack.onResultFailure(e.getMessage());
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        LogUtils.catchInfo(e.toString());
                    }
                }
            }
        });
    }

}
