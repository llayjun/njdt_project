package com.yuanye.njdt.data.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class MaterialEntity implements Serializable {

    private String materialName;

    private String scope;

    private String storageLocation;

    private String teamName;

    private String teamPhone;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String _materialName) {
        materialName = _materialName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String _scope) {
        scope = _scope;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String _storageLocation) {
        storageLocation = _storageLocation;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String _teamName) {
        teamName = _teamName;
    }

    public String getTeamPhone() {
        return teamPhone;
    }

    public void setTeamPhone(String _teamPhone) {
        teamPhone = _teamPhone;
    }
}
