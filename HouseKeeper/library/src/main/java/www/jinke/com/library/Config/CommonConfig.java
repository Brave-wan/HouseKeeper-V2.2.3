package www.jinke.com.library.Config;

import com.google.gson.Gson;

import www.jinke.com.library.bean.PageInfo;

/**
 * Created by root on 18-5-18.
 */

public class CommonConfig {
    public static String pageInfo(int page) {
        PageInfo info = new PageInfo();
        info.setCurrentPage(page);
        info.setOrder("asc");
        info.setPageSize(20);
        String pageStr = new Gson().toJson(info);
        return pageStr;
    }



}
