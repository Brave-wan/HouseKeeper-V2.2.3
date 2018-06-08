package com.jinke.housekeeper.housemanger.present;

import com.blankj.utilcode.util.ToastUtils;
import com.jinke.housekeeper.housemanger.bean.IdVerificationBean;
import com.jinke.housekeeper.housemanger.config.SignHousing;
import com.jinke.housekeeper.housemanger.http.ApiCallback;
import com.jinke.housekeeper.housemanger.http.HousingManager;
import com.jinke.housekeeper.housemanger.http.HousingResult;
import com.jinke.housekeeper.housemanger.view.IdVerificationView;

import java.util.SortedMap;

import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-15.
 */

public class IdVerificationPresent extends BasePresenter<IdVerificationView> {

    public void getCheckList(SortedMap<String, String> map) {
        if (mView != null) {
            mView.showLoading();
            HousingManager.get().addSubscription(HousingManager.get().getApiStores().getCheckList(map, SignHousing.createSign(map)), new ApiCallback<HousingResult<IdVerificationBean>>() {
                @Override
                public void onSuccess(HousingResult<IdVerificationBean> model) {
                    mView.hideLoading();
                    mView.onAuList(model.getData().getWanwan());
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
