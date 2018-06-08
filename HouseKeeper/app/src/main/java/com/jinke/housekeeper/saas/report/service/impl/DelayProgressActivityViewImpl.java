package com.jinke.housekeeper.saas.report.service.impl;

import android.content.Context;

import com.jinke.housekeeper.bean.StartDelayBean;
import com.jinke.housekeeper.saas.report.bean.NodeDelayBean;
import com.jinke.housekeeper.saas.report.config.ReportConfig;
import com.jinke.housekeeper.saas.report.http.HttpMethods;
import com.jinke.housekeeper.saas.report.http.HttpResult;
import com.jinke.housekeeper.saas.report.http.progress.ProgressSubscriber;
import com.jinke.housekeeper.saas.report.http.progress.SubscriberOnNextListener;
import com.jinke.housekeeper.saas.report.service.DelayProgressActivityBiz;
import com.jinke.housekeeper.saas.report.service.listener.DelayProgressActivityViewListener;

import java.util.SortedMap;

/**
 * Created by Administrator on 2017/9/11.
 */

public class DelayProgressActivityViewImpl implements DelayProgressActivityBiz {
    private Context context;

    public DelayProgressActivityViewImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getProcessDetail(SortedMap<String, String> map, final DelayProgressActivityViewListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<NodeDelayBean>() {
            @Override
            public void onNext(NodeDelayBean info) {
                if (info.getObj() != null) {
                    listener.getProcessDetailonNext(info);
                }
            }

            @Override
            public void onError(String Code, String Msg) {
                listener.getProcessDetailonError( Code,  Msg);

            }
        };
        HttpMethods.getInstance().getNodeDelay(new ProgressSubscriber<HttpResult<NodeDelayBean>>(onNextListener, context, true), map, ReportConfig.createSign(map));

    }
    @Override
    public void getProcessStartDetail(SortedMap<String, String> map, final DelayProgressActivityViewListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<StartDelayBean>() {
            @Override
            public void onNext(StartDelayBean info) {
                if (info.getObj() != null) {
                    listener.onStartDelayBean(info);
                }
            }

            @Override
            public void onError(String Code, String Msg) {
                listener.getProcessDetailonError(Code, Msg);

            }
        };
        HttpMethods.getInstance().getNodeStartDelay(new ProgressSubscriber<HttpResult<StartDelayBean>>(onNextListener, context, true), map, ReportConfig.createSign(map));

    }
}
