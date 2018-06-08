package com.jinke.housekeeper.view;

import com.jinke.housekeeper.base.BaseView;
import com.jinke.housekeeper.bean.MemberListBean;

import www.jinke.com.library.base.IBaseView;

/**
 * Created by Administrator on 2017/9/7.
 */

public interface MemberlistActivityView  extends IBaseView{
    void getMenberListNext(MemberListBean dataBean);

    void getMenberListError(String code, String msg);
}
