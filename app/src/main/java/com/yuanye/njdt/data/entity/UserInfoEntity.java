package com.yuanye.njdt.data.entity;


import java.io.Serializable;

public class UserInfoEntity implements Serializable {
    private int uId;
    private String uSerials;
    private String uPwd;
    private String uName;
    private int uRole;
    private String uAdd;
    private String mobile;
    private String groupId;
    private String jobNumber;
    private int begin;
    private int end;
    private String uRoleName;
    private String uGroupName;

    public int getuId() {
        return uId;
    }

    public void setuId(int _uId) {
        uId = _uId;
    }

    public String getuSerials() {
        return uSerials;
    }

    public void setuSerials(String _uSerials) {
        uSerials = _uSerials;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String _uPwd) {
        uPwd = _uPwd;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String _uName) {
        uName = _uName;
    }

    public int getuRole() {
        return uRole;
    }

    public void setuRole(int _uRole) {
        uRole = _uRole;
    }

    public String getuAdd() {
        return uAdd;
    }

    public void setuAdd(String _uAdd) {
        uAdd = _uAdd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String _mobile) {
        mobile = _mobile;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String _groupId) {
        groupId = _groupId;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String _jobNumber) {
        jobNumber = _jobNumber;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int _begin) {
        begin = _begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int _end) {
        end = _end;
    }

    public String getuRoleName() {
        return uRoleName;
    }

    public void setuRoleName(String _uRoleName) {
        uRoleName = _uRoleName;
    }

    public String getuGroupName() {
        return uGroupName;
    }

    public void setuGroupName(String _uGroupName) {
        uGroupName = _uGroupName;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "uId=" + uId +
                ", uSerials='" + uSerials + '\'' +
                ", uPwd='" + uPwd + '\'' +
                ", uName='" + uName + '\'' +
                ", uRole=" + uRole +
                ", uAdd='" + uAdd + '\'' +
                ", mobile='" + mobile + '\'' +
                ", groupId='" + groupId + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", uRoleName='" + uRoleName + '\'' +
                ", uGroupName='" + uGroupName + '\'' +
                '}';
    }
}