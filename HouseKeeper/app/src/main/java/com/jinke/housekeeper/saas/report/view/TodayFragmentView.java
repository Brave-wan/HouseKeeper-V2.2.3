package com.jinke.housekeeper.saas.report.view;

import com.jinke.housekeeper.base.BaseView;
import com.jinke.housekeeper.saas.report.bean.RectifiedBean;

/**
 * Created by Administrator on 2017/9/11.
 */

public interface TodayFragmentView {
    void getRectifiedListonNext(RectifiedBean bean);

    void getRectifiedListonError(String code, String msg);
}
