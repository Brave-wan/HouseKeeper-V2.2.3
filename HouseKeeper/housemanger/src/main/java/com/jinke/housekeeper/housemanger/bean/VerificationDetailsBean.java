package com.jinke.housekeeper.housemanger.bean;

import java.util.List;

/**
 * Created by root on 18-5-30.
 */

public class VerificationDetailsBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * image1 : https://staticfile.tq-service.com/image/LargeSystem/20180530/1b64cd07bcd44372bbbc2a14fc519de6.jpg
         * phone : 15223084076
         * ownerName : 魏爽
         * houseNo : 科技中心8-1-140
         * idCard : 500382199408125879
         * image2 : https://staticfile.tq-service.com/image/LargeSystem/20180530/0f7d75adee194d348cff1eff64b198bf.jpg
         * id : 1527668225617
         */

        private String image1;
        private String phone;
        private String ownerName;
        private String houseNo;
        private String idCard;
        private String image2;
        private String id;

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getHouseNo() {
            return houseNo;
        }

        public void setHouseNo(String houseNo) {
            this.houseNo = houseNo;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
