package com.jinke.housekeeper.saas.report.service;

import com.jinke.housekeeper.saas.report.service.listener.AllFragmentListener;
import com.jinke.housekeeper.saas.report.service.listener.TodayFragmentListener;

import java.util.SortedMap;

/**
 * Created by Administrator on 2017/9/11.
 */

public interface TodayFragmentBiz {
    void getRectifiedList(SortedMap<String, String> map, TodayFragmentListener listener);
}
