package com.housekeeper.community.ui.activity;

import android.view.View;

import com.housekeeper.community.R;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-22.
 */

public class KeyCodeActivity extends BaseActivity {
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_key_code;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.community_key_manager_borrow));
        showBackwardViewIco(R.mipmap.back_image);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }
}
