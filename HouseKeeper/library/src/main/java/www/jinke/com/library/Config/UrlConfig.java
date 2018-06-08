package www.jinke.com.library.Config;

import android.content.Context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by root on 18-5-17.
 */

public class UrlConfig {

    private static int urlType = 1;//0 本地环境 1测试环境 2正式环境1 3正式环境,4,华美美丽山
    private static String appUrl;
    private static String equipmentUrl;
    private static String patrolUrl;
    private static String reportUrl;
    private static String handoverRoomUrl;
    private static String housing;
    public static Context instance;

    //    public static final String REQUEST_URL = "http://10.15.208.53:8089/";//本地服务器
//    public static final String REQUEST_URL = "http://api.tq-service.com/";//正式服务器
//    public static final String REQUEST_URL = "http://dev-oa-api.tq-service.com/";//测试服务器

    public static int getUrlType() {
        return urlType;
    }

    public void setUrlType(int urlType) {
        this.urlType = urlType;
    }

    public static void initURL(int urlType, Context mContext) {
        instance = mContext;
        Map<String, String> stringMap = new HashMap<>();
        switch (urlType) {
            //0 本地环境 1测试环境 2正式环境1 3正式环境,4,华美美丽山
            case 0:
                stringMap = URLUtil.getNativeURL();
                break;
            //测试环境
            case 1:
                stringMap = URLUtil.getDebugURL();
                break;
            //正式环境1
            case 2:
                stringMap = URLUtil.getRegularURL1();
                break;
            //正式环境
            case 3:
                stringMap = URLUtil.getRegularURL2();
                break;
            //华美美丽山
            case 4:
                stringMap = URLUtil.getHuMei();
                break;
        }

        Iterator entryIterator = stringMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) entryIterator.next();
            switch ((String) entry.getKey()) {
                case "appUrl":
                    appUrl = (String) entry.getValue();
                    break;
                case "equipmentUrl":
                    equipmentUrl = (String) entry.getValue();
                    break;

                case "patrolUrl":
                    patrolUrl = (String) entry.getValue();
                    break;
                case "reportUrl":
                    reportUrl = (String) entry.getValue();
                    break;
                case "handoverRoomUrl":
                    handoverRoomUrl = (String) entry.getValue();
                    break;

                case "housing":
                    housing = (String) entry.getValue();
                    break;
            }
        }
    }

    public static String getAppUrl() {
        return appUrl;
    }

    public static void setAppUrl(String appUrl) {
        UrlConfig.appUrl = appUrl;
    }

    public static String getEquipmentUrl() {
        return equipmentUrl;
    }

    public static void setEquipmentUrl(String equipmentUrl) {
        UrlConfig.equipmentUrl = equipmentUrl;
    }

    public static String getPatrolUrl() {
        return patrolUrl;
    }

    public static void setHousing(String housing) {
        UrlConfig.housing = housing;
    }

    public static String getHousing() {
        return housing;
    }

    public static void setPatrolUrl(String patrolUrl) {
        UrlConfig.patrolUrl = patrolUrl;
    }

    public static String getReportUrl() {
        return reportUrl;
    }

    public static void setReportUrl(String reportUrl) {
        UrlConfig.reportUrl = reportUrl;
    }

    public static String getHandoverRoomUrl() {
        return handoverRoomUrl;
    }

    public static void setHandoverRoomUrl(String handoverRoomUrl) {
        UrlConfig.handoverRoomUrl = handoverRoomUrl;
    }
}
