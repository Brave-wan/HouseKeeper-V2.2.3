package com.jinke.housekeeper.housemanger.bean;

/**
 * Created by root on 18-5-14.
 */

public class HousingInfoBean {
    private int res;
    private String des;


    public HousingInfoBean(int res, String des) {
        this.res = res;
        this.des = des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }


}
