package com.jinke.housekeeper.presenter;

import android.content.Context;

import com.jinke.housekeeper.base.BasePresenter;
import com.jinke.housekeeper.bean.AppHandleInfo;
import com.jinke.housekeeper.bean.UserPushBean;
import com.jinke.housekeeper.service.biz.UsersFragmentBiz;
import com.jinke.housekeeper.service.impl.UsersFragmentImpl;
import com.jinke.housekeeper.service.listener.UsersFragmentListener;
import com.jinke.housekeeper.service.listener.userPushListener;
import com.jinke.housekeeper.view.UsersFragmentView;

import java.util.SortedMap;

/**
 * Created by Administrator on 2017/9/12.
 */

public class UsersFragmentPresenter extends BasePresenter<UsersFragmentView> implements UsersFragmentListener, userPushListener {
    private Context context;
    private UsersFragmentBiz biz;

    public UsersFragmentPresenter(Context context) {
        this.context = context;
        biz = new UsersFragmentImpl(context);
    }

    public void getLoginOut(SortedMap<String, String> map) {
        if (mView != null) {
            mView.showLoading();
            biz.getLoginOut(map, this);
        }

    }

    public void userPush(SortedMap<String, String> map) {
        if (mView != null) {
            mView.showLoading();
            biz.userPush(map, this);
        }

    }

    @Override
    public void getLoinOutonError(String code, String msg) {
        if (mView != null) {
            mView.hideLoading();
            mView.getLoinOutonError(code, msg);
        }

    }

    @Override
    public void getLoinOutonNext(AppHandleInfo info) {
        if (mView != null) {
            mView.getLoinOutonNext(info);
            mView.hideLoading();
        }
    }

    @Override
    public void userPushError(String code, String msg) {
        if (mView != null) {
            mView.hideLoading();
            mView.userPushError(code, msg);
        }
    }

    @Override
    public void userPushNext(UserPushBean info) {
        if (mView != null) {
            mView.userPushNext(info.getState());
            mView.hideLoading();
        }
    }
}
