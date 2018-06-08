package com.jinke.housekeeper.housemanger.bean;

/**
 * Created by root on 18-5-30.
 */

public class SessionBean {

    /**
     * sessionId : 237658dbb77344939840efde638674ca
     * staffName : 包强测试
     * ifManage : 1
     */

    private String sessionId;
    private String staffName;
    private String ifManage;
    private String token;
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getIfManage() {
        return ifManage;
    }

    public void setIfManage(String ifManage) {
        this.ifManage = ifManage;
    }
}
