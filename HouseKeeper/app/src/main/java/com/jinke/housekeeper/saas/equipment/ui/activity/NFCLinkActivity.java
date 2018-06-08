package com.jinke.housekeeper.saas.equipment.ui.activity;

import android.view.View;

import com.jinke.housekeeper.R;
import com.jinke.housekeeper.saas.equipment.base.BaseActivity;
import com.jinke.housekeeper.saas.equipment.base.BasePresenter;

public class NFCLinkActivity extends BaseActivity {

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_nfc_link;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.activity_nfc_link));
        setTitleBarBgColor(R.color.equipment_title_bg);
        setTitleColor(getResources().getColor(R.color.equipment_title_text));
        showBackwardViewIco(R.drawable.equipment_back_ico);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }
}
