package com.jinke.housekeeper.service.impl;

import android.content.Context;

import com.jinke.housekeeper.applicaption.MyApplication;
import com.jinke.housekeeper.bean.AppHandleInfo;
import com.jinke.housekeeper.bean.UpdatePwdBean;
import com.jinke.housekeeper.http.HttpMethods;
import com.jinke.housekeeper.http.HttpResult;
import com.jinke.housekeeper.http.progress.ProgressSubscriber;
import com.jinke.housekeeper.http.progress.SubscriberOnNextListener;
import com.jinke.housekeeper.service.listener.PwdActivityListener;
import com.jinke.housekeeper.service.biz.PwdActivityBiz;
import com.jinke.housekeeper.service.listener.UsersFragmentListener;

import java.util.SortedMap;

/**
 * Created by Administrator on 2017/9/7.
 */

public class PwdActivityImpl implements PwdActivityBiz {
    private Context context;

    public PwdActivityImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getUpdatePwd(SortedMap<String, String> map, final PwdActivityListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<UpdatePwdBean>() {
            @Override
            public void onNext(UpdatePwdBean info) {
                listener.getUpdatePwdNext();
            }
            @Override
            public void onError(String Code, String Msg) {
                listener.getUpdatePwdError( Code,  Msg);
            }
        };
        HttpMethods.getInstance().getUpdatePwd(new ProgressSubscriber<HttpResult<UpdatePwdBean>>(onNextListener), map, MyApplication.createSign(map));
    }

    @Override
    public void getLoginOut(SortedMap<String, String> map, final UsersFragmentListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<AppHandleInfo>() {
            @Override
            public void onNext(AppHandleInfo info) {
                if (info != null) {
                    listener.getLoinOutonNext(info);}
            }

            @Override
            public void onError(String Code, String Msg) {
                listener.getLoinOutonError(Code,  Msg);

            }
        };
        HttpMethods.getInstance().getuserLoginOut(new ProgressSubscriber<HttpResult<AppHandleInfo>>(onNextListener), map, MyApplication.createSign(map));

    }
}
