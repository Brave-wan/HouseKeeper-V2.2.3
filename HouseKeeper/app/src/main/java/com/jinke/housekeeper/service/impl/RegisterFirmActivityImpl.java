package com.jinke.housekeeper.service.impl;

import android.content.Context;

import com.jinke.housekeeper.bean.RegisterProjectBean;
import com.jinke.housekeeper.http.HttpMethods;
import com.jinke.housekeeper.http.HttpResult;
import com.jinke.housekeeper.http.progress.ProgressSubscriber;
import com.jinke.housekeeper.http.progress.SubscriberOnNextListener;
import com.jinke.housekeeper.service.biz.RegisterFirmActivityBiz;
import com.jinke.housekeeper.service.listener.RegisterFirmActivityListener;

import java.util.SortedMap;

/**
 * Created by Administrator on 2017/9/15.
 */

public class RegisterFirmActivityImpl implements RegisterFirmActivityBiz {
    private Context context;

    public RegisterFirmActivityImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getXMList(SortedMap<String, String> map, final RegisterFirmActivityListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<RegisterProjectBean>() {
            @Override
            public void onNext(RegisterProjectBean info) {
                listener.getXMListonNext(info);
            }

            @Override
            public void onError(String Code, String Msg) {
                listener.getXMListonError( Code,  Msg);
            }
        };
        HttpMethods.getInstance().getXMListData(new ProgressSubscriber<HttpResult<RegisterProjectBean>>(onNextListener), map);
    }
}
