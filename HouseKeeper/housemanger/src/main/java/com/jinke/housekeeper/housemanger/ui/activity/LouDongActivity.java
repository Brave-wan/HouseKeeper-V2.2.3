package com.jinke.housekeeper.housemanger.ui.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jinke.housekeeper.housemanger.R;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-22.
 */

public class LouDongActivity extends BaseActivity {
    RecyclerView rv_lou_dong_list;
    BaseQuickAdapter<String, BaseViewHolder> adapter;
    private List<String> list = new ArrayList<>();

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_lou_dong;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.housing_manager_contract_id_number));
        showBackwardViewIco(R.mipmap.back_image);
        rv_lou_dong_list = findViewById(R.id.rv_lou_dong_list);
        initAdapter();
    }

    private void initAdapter() {

        list.add("");
        list.add("");
        list.add("");
        list.add("");

        rv_lou_dong_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_lou_dong, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

            }
        };
        rv_lou_dong_list.setAdapter(adapter);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }
}
