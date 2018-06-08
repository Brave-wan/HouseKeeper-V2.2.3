package com.jinke.housekeeper.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.jinke.housekeeper.applicaption.MyApplication;
import com.jinke.housekeeper.base.BasePresenter;
import com.jinke.housekeeper.bean.OpenIdBean;
import com.jinke.housekeeper.housemanger.config.SignHousing;
import com.jinke.housekeeper.housemanger.http.ApiCallback;
import com.jinke.housekeeper.housemanger.http.HousingManager;
import com.jinke.housekeeper.housemanger.http.HousingResult;
import com.jinke.housekeeper.saas.report.bean.SessionBean;
import com.jinke.housekeeper.saas.report.http.HttpMethods;
import com.jinke.housekeeper.saas.report.http.HttpResult;
import com.jinke.housekeeper.saas.report.http.progress.ProgressSubscriber;
import com.jinke.housekeeper.saas.report.http.progress.SubscriberOnNextListener;
import com.jinke.housekeeper.saas.report.service.WorkBenchFragmentBiz;

import www.jinke.com.library.db.UserInfo;

import com.jinke.housekeeper.service.impl.WorkBenchFragmentImpl;
import com.jinke.housekeeper.service.listener.WorkBenchFragmentListener;
import com.jinke.housekeeper.view.WorkBenchFragmentView;

import org.litepal.crud.DataSupport;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/9/6.
 */

public class WorkBenchFragmentPresenter extends BasePresenter<WorkBenchFragmentView> implements WorkBenchFragmentListener {
    private Context mContext;
    private WorkBenchFragmentBiz mBiz;

    public WorkBenchFragmentPresenter(Context mContext) {
        this.mContext = mContext;
        mBiz = new WorkBenchFragmentImpl(mContext);
    }


    /**
     * 巡更
     *
     * @param map
     */
    public void getMapPoint(SortedMap<String, String> map) {
        if (mView != null){
            mView.showLoading();
            mBiz.getMapPoint(map, this);
        }

    }

    @Override
    public void getMapPointNext(OpenIdBean info) {
        if (mView != null){
            mView.hideLoading();
            mView.getMapPointNext(info);
        }
    }

    @Override
    public void getMapPointError(String code, String msg) {
        if (mView != null){
            mView.hideLoading();
            mView.getMapPointError(code, msg);
        }
    }

    public void getToken(SortedMap<String, String> map) {
        //获取品质巡检openId
        SubscriberOnNextListener onNextListener = new SubscriberOnNextListener<SessionBean>() {
            @Override
            public void onNext(SessionBean info) {
                mView.onQualityInspect(info);
            }

            @Override
            public void onError(String Code, String Msg) {
                ToastUtils.showShort(Msg);
            }
        };
        HttpMethods.getInstance().getToken(new ProgressSubscriber<HttpResult<SessionBean>>(onNextListener, mContext, true), map, MyApplication.createSign(map));
    }

    public void getTokenAndUser(String open_id) {
        SortedMap<String, String> map = new TreeMap<>();
        UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
        map.put("openId", open_id);
        map.put("projectId", userInfo.getLeftOrgId());
        HousingManager.get().addSubscription(HousingManager.get().getApiStores().getToken(map, SignHousing.createSign(map)), new ApiCallback<HousingResult<com.jinke.housekeeper.housemanger.bean.SessionBean>>() {
            @Override
            public void onSuccess(HousingResult<com.jinke.housekeeper.housemanger.bean.SessionBean> model) {
                mView.onTokenAndUser(model.getData());
            }

            @Override
            public void onFailure(HousingResult result) {
                ToastUtils.showShort("授权失败!");
            }
        });
    }
}
