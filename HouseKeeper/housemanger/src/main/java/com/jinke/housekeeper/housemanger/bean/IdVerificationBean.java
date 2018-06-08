package com.jinke.housekeeper.housemanger.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 18-5-30.
 */

public class IdVerificationBean implements Serializable{


    private List<WanwanBean> wanwan;

    public List<WanwanBean> getWanwan() {
        return wanwan;
    }

    public void setWanwan(List<WanwanBean> wanwan) {
        this.wanwan = wanwan;
    }

    public static class WanwanBean implements Serializable{
        /**
         * id : 1527667728916,1527667728917,1527667728918,1527667728919,1527668225616,1527668225617
         * status : N
         * houseNo : 科技中心8-1-140
         */

        private String id;
        private String status;
        private String houseNo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getHouseNo() {
            return houseNo;
        }

        public void setHouseNo(String houseNo) {
            this.houseNo = houseNo;
        }
    }
}
