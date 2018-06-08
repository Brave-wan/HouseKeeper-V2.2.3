package com.jinke.housekeeper.knowledge.prsent;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.jinke.housekeeper.knowledge.bean.KnowledgeInfo;
import com.jinke.housekeeper.knowledge.http.ApiCallback;
import com.jinke.housekeeper.knowledge.http.HttpManager;
import com.jinke.housekeeper.knowledge.http.HttpResult;
import com.jinke.housekeeper.knowledge.view.LibActivityView;

import java.util.SortedMap;

import com.jinke.housekeeper.knowledge.http.KnowledgeSign;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by Administrator on 2017/9/11.
 */

public class LibActivityPresenter extends BasePresenter<LibActivityView> {
    private Context context;

    public LibActivityPresenter(Context context) {
        this.context = context;
    }

    public void getLore(SortedMap<String, String> map) {
        if (mView != null) {
            HttpManager.get().addSubscription(HttpManager.get().getApiStores().getLore(map, KnowledgeSign.createSign(map)), new ApiCallback<HttpResult<KnowledgeInfo>>() {
                @Override
                public void onSuccess(HttpResult<KnowledgeInfo> model) {
                    mView.getLoreOnNext(model.getData());
                }

                @Override
                public void onFailure(HttpResult result) {
                    ToastUtils.showShort(result.getErrmsg());
                }
            });
        }

    }


}
