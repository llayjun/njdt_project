package com.yuanye.njdt.presenter.service;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public interface IMyService {

    public void reqServer(Serializable _serializable, String _headerValue, IReqResultCallBack _iReqResultCallBack);

    public void reqServerWithNoParam(String _headerValue, IReqResultCallBack _iReqResultCallBack);

    public void reqDownFileWithNoParam(String _url, final String _fileDir, final String _fileName, final IReqResultDownFileCallBack _iReqResultDownFileCallBack);

}
