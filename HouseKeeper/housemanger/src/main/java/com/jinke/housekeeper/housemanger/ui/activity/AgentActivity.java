package com.jinke.housekeeper.housemanger.ui.activity;

import android.view.View;

import com.jinke.housekeeper.housemanger.R;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-15.
 * 添加代理人
 */

public class AgentActivity extends BaseActivity {
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_agent;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.housing_manager_contract_add_agent));
        showBackwardViewIco(R.drawable.icon_housing_manager_back);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }
}
