package com.jinke.housekeeper.view;

import com.jinke.housekeeper.bean.RegisterProjectBean;

import www.jinke.com.library.base.IBaseView;

/**
 * Created by Administrator on 2017/9/15.
 */

public interface RegisterFirmActivityView  extends IBaseView{
    void getXMListonNext(RegisterProjectBean info);

    void getXMListonError(String code, String msg);
}
