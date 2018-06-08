package com.jinke.housekeeper.view;

import com.jinke.housekeeper.bean.AppHandleInfo;

import www.jinke.com.library.base.IBaseView;

/**
 * Created by Administrator on 2017/9/12.
 */

public interface UsersFragmentView  extends IBaseView{
    void getLoinOutonNext(AppHandleInfo info);

    void getLoinOutonError(String code, String msg);

    void userPushNext(String info);

    void userPushError(String code, String msg);
}
