package com.jinke.housekeeper.knowledge.bean;

/**
 * Created by root on 18-5-17.
 */

public class KnowledgeApplicationBean {
    private int res;
    private String des;


    public KnowledgeApplicationBean(int res, String des) {
        this.res = res;
        this.des = des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getDes() {
        return des;
    }
}
