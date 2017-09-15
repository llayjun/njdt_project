package com.yuanye.njdt.ui.callback;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public interface PdfDownStateListener {

    void onPdfResultStart();

    void onPdfResultProgress(int _progress);

    void onPdfResultEnd();

    void onPdfResultFailure(String _result);

}
