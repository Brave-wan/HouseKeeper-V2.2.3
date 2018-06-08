package com.jinke.housekeeper.housemanger.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jinke.housekeeper.housemanger.R;
import com.jinke.housekeeper.housemanger.bean.HousingInfoBean;
import com.jinke.housekeeper.housemanger.bean.SessionBean;
import com.jinke.housekeeper.housemanger.config.SignHousing;
import com.jinke.housekeeper.housemanger.http.ApiCallback;
import com.jinke.housekeeper.housemanger.http.HousingManager;
import com.jinke.housekeeper.housemanger.http.HousingResult;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;
import www.jinke.com.library.db.UserInfo;

/**
 * Created by root on 18-5-14.
 * 接房管理
 */

public class HousingManagerActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    RecyclerView rv_housing_manager;
    BaseQuickAdapter<HousingInfoBean, BaseViewHolder> adapter;
    List<HousingInfoBean> list = new ArrayList<>();
    private String open_id;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_housing_home;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.housing_manager_home_title));
        showBackwardViewIco(R.drawable.icon_housing_manager_back);
        rv_housing_manager = findViewById(R.id.rv_housing_manager);
        open_id = getIntent().getStringExtra("open_id");
        //验证权限

        initAdapter();
    }

    private void initAdapter() {
        //合同管理
        list.add(new HousingInfoBean(R.drawable.icon_housing_manager_contract, getString(R.string.housing_manager_home_contract)));
        //身份核实
        list.add(new HousingInfoBean(R.drawable.icon_housing_manager_id, getString(R.string.housing_manager_entity_verification)));
        //物业缴费
        list.add(new HousingInfoBean(R.drawable.icon_housing_manager_property_paymen, getString(R.string.housing_manager_property_payment)));
        //钥匙托管
        list.add(new HousingInfoBean(R.drawable.icon_housing_manager_key_hosting, getString(R.string.housing_manager_key_hosting)));

        rv_housing_manager.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new BaseQuickAdapter<HousingInfoBean, BaseViewHolder>(R.layout.item_housing_mamager, list) {
            @Override
            protected void convert(BaseViewHolder helper, HousingInfoBean item) {
                ImageView item_workbench_image = helper.itemView.findViewById(R.id.item_workbench_image);
                TextView item_workbench_text = helper.itemView.findViewById(R.id.item_workbench_text);
                item_workbench_image.setBackgroundResource(item.getRes());
                item_workbench_text.setText(item.getDes());
            }
        };
        adapter.setOnItemClickListener(this);
        rv_housing_manager.setAdapter(adapter);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            //合同管理
            case 0:
                startActivity(new Intent(HousingManagerActivity.this, ContractManagerActivity.class));
                break;
            //身份核实
            case 1:
                startActivity(new Intent(HousingManagerActivity.this, IdVerificationActivity.class));
                break;
            //物业缴费
            case 2:
                break;
            //钥匙托管
            case 3:
                break;
        }
    }


}
