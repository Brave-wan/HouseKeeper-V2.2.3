package com.jinke.housekeeper.bean;

import java.util.List;

/**
 * Created by root on 18-5-28.
 */

public class StartDelayBean {


    private List<ObjBean> obj;

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * audioAddr :
         * audioLen : 0
         * userName : 万德盈
         * moblePhone : qq99
         * remark : 抢单成功
         * time : 2018-05-27 14:43:41
         */

        private String audioAddr;
        private int audioLen;
        private String userName;
        private String moblePhone;
        private String remark;
        private String time;

        public String getAudioAddr() {
            return audioAddr;
        }

        public void setAudioAddr(String audioAddr) {
            this.audioAddr = audioAddr;
        }

        public int getAudioLen() {
            return audioLen;
        }

        public void setAudioLen(int audioLen) {
            this.audioLen = audioLen;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMoblePhone() {
            return moblePhone;
        }

        public void setMoblePhone(String moblePhone) {
            this.moblePhone = moblePhone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
