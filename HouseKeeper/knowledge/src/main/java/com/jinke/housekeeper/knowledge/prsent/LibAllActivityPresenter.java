package com.jinke.housekeeper.knowledge.prsent;

import android.content.Context;


import com.blankj.utilcode.util.ToastUtils;
import com.jinke.housekeeper.knowledge.bean.LibAllInfo;
import com.jinke.housekeeper.knowledge.http.ApiCallback;
import com.jinke.housekeeper.knowledge.http.HttpManager;
import com.jinke.housekeeper.knowledge.http.HttpResult;
import com.jinke.housekeeper.knowledge.http.KnowledgeSign;
import com.jinke.housekeeper.knowledge.view.LibAllActivityView;

import java.util.SortedMap;

import www.jinke.com.library.base.BasePresenter;

/**
 * Created by Administrator on 2017/9/11.
 */

public class LibAllActivityPresenter extends BasePresenter<LibAllActivityView> {
    private Context context;

    public LibAllActivityPresenter(Context context) {
        this.context = context;
    }

    public void getScenePage(SortedMap<String, String> map) {
        if (mView != null) {
            HttpManager.get().addSubscription(HttpManager.get().getApiStores()
                    .getScenePageData(map, KnowledgeSign.createSign(map)), new ApiCallback<HttpResult<LibAllInfo>>() {
                @Override
                public void onSuccess(HttpResult<LibAllInfo> model) {
                    mView.getScenePageOnNext(model.getData());
                }

                @Override
                public void onFailure(HttpResult result) {
                    ToastUtils.showShort(result.getErrmsg());
                }
            });
        }

    }

}
