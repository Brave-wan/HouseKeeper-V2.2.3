package com.housekeeper.community.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.housekeeper.community.R;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-22.
 */

public class BorrowKeyActivity extends BaseActivity implements View.OnClickListener {
    TextView tx_generate_code;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_borrow_key;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.community_key_manager_borrow));
        showBackwardViewIco(R.mipmap.back_image);
        tx_generate_code = findViewById(R.id.tx_generate_code);
        tx_generate_code.setOnClickListener(this);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tx_generate_code) {
            startActivity(new Intent(this, KeyCodeActivity.class));
        }

    }
}
