package com.jinke.housekeeper.saas.report.service.listener;

import com.jinke.housekeeper.bean.RegisterProjectBean;

/**
 * Created by Administrator on 2017/9/15.
 */

public interface RegisterFirmActivityListener {
    void getXMListonError(String code, String msg);

    void getXMListonNext(RegisterProjectBean info);
}
