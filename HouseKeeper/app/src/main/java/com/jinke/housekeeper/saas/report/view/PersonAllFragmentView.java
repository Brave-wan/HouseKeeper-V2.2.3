package com.jinke.housekeeper.saas.report.view;

import com.jinke.housekeeper.saas.report.bean.SelfHistoryBean;

/**
 * Created by Administrator on 2017/9/11.
 */

public interface PersonAllFragmentView {
    void getSelfHistoryListonError(String code, String msg);

    void getSelfHistoryListonNext(SelfHistoryBean bean);
}
