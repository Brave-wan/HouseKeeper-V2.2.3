package com.jinke.housekeeper.saas.report.config;

import com.adtech.sys.util.Encrypt;
import com.adtech.sys.util.MD5Util;
import www.jinke.com.library.db.SessionInfo;
import www.jinke.com.library.db.UserInfo;

import com.blankj.utilcode.util.StringUtils;
import com.jinke.housekeeper.utils.CommonlyUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.jinke.housekeeper.applicaption.MyApplication.getInstance;

/**
 * Created by root on 18-4-27.
 */

public class ReportConfig {

    public static String createSign(SortedMap<String, String> map) {
        String tokenCommonlyUtils;
        SortedMap<Object, Object> sortedMap = new TreeMap<>();
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry sign = (Map.Entry) it.next();
            Object k = sign.getKey();
            Object v = sign.getValue();
            sortedMap.put(k, v);
        }

        SessionInfo info = CommonlyUtils.getSessionInfo(getInstance());
        //请求加密
        if (!StringUtils.isEmpty(info.getUserName()) && !StringUtils.isEmpty(info.getPassword())) {
            tokenCommonlyUtils = Encrypt.md5(info.getUserName() + "jkwycruise");
        } else {
            UserInfo userInfo = CommonlyUtils.getUserInfo(getInstance());
            tokenCommonlyUtils = Encrypt.md5(userInfo.getUserName() + "jkwycruise");
        }

        return MD5Util.createSign(tokenCommonlyUtils, "UTF-8", sortedMap);
    }
}
