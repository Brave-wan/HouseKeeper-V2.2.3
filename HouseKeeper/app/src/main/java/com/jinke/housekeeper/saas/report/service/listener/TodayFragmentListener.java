package com.jinke.housekeeper.saas.report.service.listener;

import com.jinke.housekeeper.saas.report.bean.RectifiedBean;

/**
 * Created by Administrator on 2017/9/11.
 */

public interface TodayFragmentListener {
    void getRectifiedListonNext(RectifiedBean bean);

    void getRectifiedListonError(String code, String msg);
}
