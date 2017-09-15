package com.yuanye.njdt.data.apidata;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class EmergencyListReq implements Serializable {

    private String title;//标题（搜索用到的）
    private int pageIndex;//从0开始
    private String sortDir;//应急事件填写：desc,历史事件填写：asc
    private int status;//应急事件填写：0,历史事件填写：50

    public EmergencyListReq(String _title, int _pageIndex, String _sortDir, int _status) {
        title = _title;
        pageIndex = _pageIndex;
        sortDir = _sortDir;
        status = _status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String _title) {
        title = _title;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int _pageIndex) {
        pageIndex = _pageIndex;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String _sortDir) {
        sortDir = _sortDir;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int _status) {
        status = _status;
    }
}
