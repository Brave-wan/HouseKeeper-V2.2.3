package com.jinke.housekeeper.bean;

import java.util.List;

/**
 * Created by root on 7/01/17.
 */

public class LibAllInfo {


    private List<ListObjBean> listObj;

    public List<ListObjBean> getListObj() {
        return listObj;
    }

    public void setListObj(List<ListObjBean> listObj) {
        this.listObj = listObj;
    }

    public static class ListObjBean {
        /**
         * id : 1481619991156
         * imgurl :
         * name : 消防栓
         */

        private long id;
        private String imgurl;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
