package com.jinke.housekeeper.knowledge.http;


import android.content.Context;
import android.util.Log;

import com.adtech.sys.util.Encrypt;
import com.adtech.sys.util.MD5Util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import www.jinke.com.library.utils.SharedPreferencesUtils;

/**
 * Created by root on 18-5-17.
 */

public class KnowledgeSign {
    static Context mContext;

    public void init(Context mContext) {
        this.mContext = mContext;
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
        String pwd = SharedPreferencesUtils.init(mContext).getString("pwd");
        //请求加密 5c0dbb90e18f692d4874aab2cda1540e
        tokenCommonlyUtils = Encrypt.md5(userName + Encrypt.md5(pwd) + "jkwycruise");
        String key = MD5Util.createSign(tokenCommonlyUtils, "UTF-8", sortedMap);
        return key;
    }

    public static String getUserId(){

        return  SharedPreferencesUtils.init(mContext).getString("userId");
    }
}
