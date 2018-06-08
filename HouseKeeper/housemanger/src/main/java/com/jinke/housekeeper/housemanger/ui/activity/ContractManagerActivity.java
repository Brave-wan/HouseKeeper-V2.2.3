package com.jinke.housekeeper.housemanger.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jinke.housekeeper.housemanger.R;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-15.
 */

public class ContractManagerActivity extends BaseActivity implements View.OnClickListener {
    TextView tx_add_agent;
    RecyclerView rv_contract_list;

    BaseQuickAdapter<String, BaseViewHolder> adapter;
    List<String> list = new ArrayList<>();

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contract_manager;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.housing_manager_home_contract));
        showBackwardViewIco(R.drawable.icon_housing_manager_back);
        tx_add_agent = findViewById(R.id.tx_add_agent);
        tx_add_agent.setOnClickListener(this);
        rv_contract_list = findViewById(R.id.rv_contract_list);
        initAdapter();
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tx_add_agent) {
            startActivity(new Intent(this, AgentActivity.class));
        }
    }

    public void initAdapter() {
        rv_contract_list.setLayoutManager(new LinearLayoutManager(this));
        list.add("");
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_agent_list, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

            }
        };
        rv_contract_list.setAdapter(adapter);

    }
}
