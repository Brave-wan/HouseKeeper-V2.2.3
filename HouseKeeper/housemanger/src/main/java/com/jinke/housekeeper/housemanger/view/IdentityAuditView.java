package com.jinke.housekeeper.housemanger.view;

import com.jinke.housekeeper.housemanger.bean.VerificationDetailsBean;

import www.jinke.com.library.base.IBaseView;

/**
 * Created by root on 18-5-30.
 */

public interface IdentityAuditView  extends IBaseView{
    void onVerificationDetailsBean(VerificationDetailsBean bean);
    void onCheckOwner(String status);
}
