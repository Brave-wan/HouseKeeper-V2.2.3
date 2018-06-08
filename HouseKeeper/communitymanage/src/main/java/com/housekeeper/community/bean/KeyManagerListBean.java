package com.housekeeper.community.bean;

/**
 * Created by root on 18-5-22.
 */

public class KeyManagerListBean {
    private String applicationName;
    private int applicationIcon;

    public KeyManagerListBean(String applicationName, int applicationIcon) {
        this.applicationIcon = applicationIcon;
        this.applicationName = applicationName;
    }

    public void setApplicationIcon(int applicationIcon) {
        this.applicationIcon = applicationIcon;
    }

    public int getApplicationIcon() {
        return applicationIcon;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }
}
