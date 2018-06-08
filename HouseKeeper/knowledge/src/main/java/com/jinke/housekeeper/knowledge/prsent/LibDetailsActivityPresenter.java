package com.jinke.housekeeper.knowledge.prsent;

import android.content.Context;


import com.blankj.utilcode.util.ToastUtils;
import com.jinke.housekeeper.knowledge.bean.LibDetailsInfo;
import com.jinke.housekeeper.knowledge.http.ApiCallback;
import com.jinke.housekeeper.knowledge.http.HttpManager;
import com.jinke.housekeeper.knowledge.http.HttpResult;
import com.jinke.housekeeper.knowledge.http.KnowledgeSign;
import com.jinke.housekeeper.knowledge.view.LibDetailsActivityView;

import java.util.SortedMap;

import www.jinke.com.library.base.BasePresenter;

/**
 * Created by Administrator on 2017/9/11.
 */

public class LibDetailsActivityPresenter extends BasePresenter<LibDetailsActivityView> {
    private Context context;


    public LibDetailsActivityPresenter(Context context) {
        this.context = context;
    }

    public void getDetailsData(SortedMap<String, String> map) {
        if (mView != null) {
            HttpManager.get().addSubscription(HttpManager.get().getApiStores().getKeyId(map, KnowledgeSign.createSign(map)),
                    new ApiCallback<HttpResult<LibDetailsInfo>>() {
                        @Override
                        public void onSuccess(HttpResult<LibDetailsInfo> model) {
                            mView.getDetailsDataOnNext(model.getData());
                        }

                        @Override
                        public void onFailure(HttpResult result) {
                            ToastUtils.showShort(result.getErrmsg());
                        }
                    });
        }
    }

//    @Override
//    public void getDetailsDataonNext(LibDetailsInfo info) {
//        if (mView != null)
//            mView.getDetailsDataonNext(info);
//    }
//
//    @Override
//    public void getDetailsDataonError(String code, String msg) {
//        if (mView != null)
//            mView.getDetailsDataonError(code, msg);
//    }
}
