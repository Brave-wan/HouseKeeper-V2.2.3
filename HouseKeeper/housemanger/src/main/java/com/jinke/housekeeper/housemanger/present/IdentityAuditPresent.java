package com.jinke.housekeeper.housemanger.present;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.jinke.housekeeper.housemanger.bean.VerificationDetailsBean;
import com.jinke.housekeeper.housemanger.config.SignHousing;
import com.jinke.housekeeper.housemanger.http.ApiCallback;
import com.jinke.housekeeper.housemanger.http.HousingManager;
import com.jinke.housekeeper.housemanger.http.HousingResult;
import com.jinke.housekeeper.housemanger.view.IdentityAuditView;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import www.jinke.com.library.base.BasePresenter;
import www.jinke.com.library.utils.SharedPreferencesUtils;

/**
 * Created by root on 18-5-30.
 */

public class IdentityAuditPresent extends BasePresenter<IdentityAuditView> {

    Context mContext;

    public IdentityAuditPresent(Context mContext) {
        this.mContext = mContext;

    }

    public void getQueryOwnerCheck(String id) {
        if (mView != null) {
            mView.showLoading();
            SortedMap<String, String> map = new TreeMap<>();
            map.put("ownerId", id);
            map.put("token", SharedPreferencesUtils.init(mContext).getString("housing_sessionId"));
            HousingManager.get().addSubscription(HousingManager.get().getApiStores().getQueryOwnerCheck(map, SignHousing.createSign(map)),
                    new ApiCallback<HousingResult<VerificationDetailsBean>>() {
                        @Override
                        public void onSuccess(HousingResult<VerificationDetailsBean> model) {
                            mView.onVerificationDetailsBean(model.getData());
                            mView.hideLoading();
                        }

                        @Override
                        public void onFailure(HousingResult result) {
                            ToastUtils.showShort(result.getErrmsg());
                            mView.hideLoading();
                        }
                    });
        }
    }

    public void getCheckOwner(String ownerId, String remark, final String status) {
        if (mView != null) {
            mView.showLoading();
            SortedMap<String, String> map = new TreeMap<>();
            map.put("ownerId", ownerId);
            map.put("status", status);
            map.put("remark", remark);
            map.put("token", SharedPreferencesUtils.init(mContext).getString("housing_sessionId"));
            HousingManager.get().addSubscription(HousingManager.get().getApiStores().getCheckOwner(map, SignHousing.createSign(map)), new ApiCallback() {
                @Override
                public void onSuccess(Object model) {
                    mView.onCheckOwner(status);
                    mView.hideLoading();
                }

                @Override
                public void onFailure(HousingResult result) {
                    ToastUtils.showShort(result.getErrmsg());
                    mView.hideLoading();
                }
            });
        }
    }
}
