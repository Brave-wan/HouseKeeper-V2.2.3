package com.jinke.housekeeper.saas.report.service.impl;

import android.content.Context;

import com.jinke.housekeeper.saas.report.bean.AllReportBean;
import com.jinke.housekeeper.saas.report.config.ReportConfig;
import com.jinke.housekeeper.saas.report.http.HttpMethods;
import com.jinke.housekeeper.saas.report.http.HttpResult;
import com.jinke.housekeeper.saas.report.http.progress.ProgressSubscriber;
import com.jinke.housekeeper.saas.report.http.progress.SubscriberOnNextListener;
import com.jinke.housekeeper.saas.report.service.InquireYesterdayFragmentBiz;
import com.jinke.housekeeper.saas.report.service.listener.InquireYesterdayFragmentListener;

import java.util.SortedMap;

/**
 * Created by Administrator on 2017/9/11.
 */

public class InquireYesterdayFragmentImpl implements InquireYesterdayFragmentBiz {
    private Context context;

    public InquireYesterdayFragmentImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getAllReportList(SortedMap<String, String> map, final InquireYesterdayFragmentListener listener) {
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<AllReportBean>() {
            @Override
            public void onNext(AllReportBean bean) {
                listener.getAllReportListonNext(bean);
            }

            @Override
            public void onError(String Code, String Msg) {
                listener.getAllReportListonError( Code,  Msg);

            }
        };
        HttpMethods.getInstance().getAllReport(new ProgressSubscriber<HttpResult<AllReportBean>>(onNextListener,context, false), map, ReportConfig.createSign(map));

    }
}
