package com.jinke.housekeeper.presenter;

import android.content.Context;

import com.jinke.housekeeper.base.BasePresenter;
import com.jinke.housekeeper.bean.StatisticsgrInfo;
import com.jinke.housekeeper.service.biz.PersonalFragmentBiz;
import com.jinke.housekeeper.service.impl.PersonalFragmentImpl;
import com.jinke.housekeeper.service.listener.PersonalFragmentListener;
import com.jinke.housekeeper.view.PersonalFragmentView;

import java.util.SortedMap;

/**
 * Created by Administrator on 2017/9/7.
 */

public class PersonalFragmentPresenter extends BasePresenter<PersonalFragmentView> implements PersonalFragmentListener {
    private Context context;
    private PersonalFragmentBiz mBiz;

    public PersonalFragmentPresenter(Context context) {
        this.context = context;
        mBiz = new PersonalFragmentImpl(context);
    }

    public void getStatisticsgr(SortedMap<String, String> getStatisticsgr) {
        if (mView != null) {
            mView.showLoading();
            mBiz.getStatisticsgr(getStatisticsgr, this);
        }

    }

    @Override
    public void getStatisticsgrError(String code, String msg) {
        if (mView != null) {
            mView.hideLoading();
            mView.getStatisticsgrError(code, msg);
        }
    }

    @Override
    public void getStatisticsgrNext(StatisticsgrInfo info) {
        if (mView != null) {
            mView.hideLoading();
            mView.getStatisticsgrNext(info);
        }
    }

    public void getIndStaSponIns(SortedMap<String, String> map) {
        mBiz.getIndStaSponIns(map, this);
    }

    @Override
    public void getIndStaSponInsNext(StatisticsgrInfo info) {
        if (mView != null) {
            mView.hideLoading();
            mView.getIndStaSponInsNext(info);
        }
    }

    @Override
    public void getIndStaSponInsError(String code, String msg) {
        if (mView != null) {
            mView.hideLoading();
            mView.getIndStaSponInsError(code, msg);
        }
    }
}
