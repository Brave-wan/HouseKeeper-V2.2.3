package com.jinke.housekeeper.view;

import www.jinke.com.library.base.IBaseView;
import www.jinke.com.library.db.LoginInfo;

/**
 * Created by Administrator on 2017/9/15.
 */

public interface LoginActivityView extends IBaseView{
    void getUserLoginDataonError(String code, String msg);

    void getUserLoginDataonNext(LoginInfo info);
}
