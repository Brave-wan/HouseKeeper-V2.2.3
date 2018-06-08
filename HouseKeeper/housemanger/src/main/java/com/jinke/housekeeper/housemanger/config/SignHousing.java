package com.jinke.housekeeper.housemanger.config;

import android.content.Context;

import com.adtech.sys.util.Encrypt;
import com.adtech.sys.util.MD5Util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import www.jinke.com.library.utils.SharedPreferencesUtils;

/**
 * Created by root on 18-5-30.
 */

public class SignHousing {

    public static Context mContext;


    public static void init(Context context) {
        mContext = context;
    }

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
        String userName = SharedPreferencesUtils.init(mContext).getString("userName");
        tokenCommonlyUtils = Encrypt.md5(userName + "jkwyhouse");
        String key = MD5Util.createSign(tokenCommonlyUtils, "UTF-8", sortedMap);
        return key;
    }

    public static String getUserId() {
        return SharedPreferencesUtils.init(mContext).getString("userId");
    }


}
