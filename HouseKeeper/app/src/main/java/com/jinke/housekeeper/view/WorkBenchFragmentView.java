package com.jinke.housekeeper.view;

import com.jinke.housekeeper.bean.OpenIdBean;
import com.jinke.housekeeper.saas.report.bean.SessionBean;

import www.jinke.com.library.base.IBaseView;

/**
 * Created by Administrator on 2017/9/6.
 */

public interface WorkBenchFragmentView  extends IBaseView{
    void getMapPointNext(OpenIdBean info);

    void getMapPointError(String code, String msg);

    void onQualityInspect(SessionBean info);

    void onTokenAndUser(com.jinke.housekeeper.housemanger.bean.SessionBean data);
}
