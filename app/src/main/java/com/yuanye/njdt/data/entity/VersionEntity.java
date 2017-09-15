package com.yuanye.njdt.data.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class VersionEntity implements Serializable {

    private String describe;

    private int versionCode;

    private String downloadUrl;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String _describe) {
        describe = _describe;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int _versionCode) {
        versionCode = _versionCode;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String _downloadUrl) {
        downloadUrl = _downloadUrl;
    }
}
