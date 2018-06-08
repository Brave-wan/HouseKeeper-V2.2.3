package com.jinke.housekeeper.housemanger.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jinke.housekeeper.housemanger.R;
import com.jinke.housekeeper.housemanger.bean.IdVerificationBean;
import com.jinke.housekeeper.housemanger.present.IdVerificationPresent;
import com.jinke.housekeeper.housemanger.view.IdVerificationView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import www.jinke.com.library.Config.CommonConfig;
import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.db.UserInfo;
import www.jinke.com.library.utils.SharedPreferencesUtils;

/**
 * Created by root on 18-5-14.
 * 身份核实
 */

public class IdVerificationActivity extends BaseActivity<IdVerificationView, IdVerificationPresent> implements OnLoadmoreListener, IdVerificationView, OnRefreshListener, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {
    RecyclerView verification_list;
    SmartRefreshLayout smart_layout;
    TextView tx_verification_search;
    EditText ed_tx_verification_search;
    BaseQuickAdapter<IdVerificationBean.WanwanBean, BaseViewHolder> adapter;

    private UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
    List<IdVerificationBean.WanwanBean> list = new ArrayList<>();
    private int page = 1;
    private String key = "";

    @Override
    public IdVerificationPresent initPresenter() {
        return new IdVerificationPresent();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dd_verification;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.housing_manager_entity_verification));
        showBackwardViewIco(R.drawable.icon_housing_manager_back);
        verification_list = findViewById(R.id.verification_list);
        tx_verification_search = findViewById(R.id.tx_verification_search);
        ed_tx_verification_search = findViewById(R.id.ed_tx_verification_search);
        tx_verification_search.setOnClickListener(this);
        smart_layout = findViewById(R.id.smart_layout);
        smart_layout.autoLoadmore();
        verification_list.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();

        smart_layout.setOnRefreshListener(this);
        smart_layout.setOnLoadmoreListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        page = 1;
        list.clear();
        getMap(page, key);
    }

    public void getMap(int page, String key) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("projectId", userInfo.getLeftOrgId());
        map.put("houseNo", key);
        map.put("token", SharedPreferencesUtils.init(this).getString("housing_sessionId"));
        map.put("pageInfo", CommonConfig.pageInfo(page));
        presenter.getCheckList(map);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tx_verification_search) {
            key = ed_tx_verification_search.getText().toString().trim();
            if (StringUtils.isEmpty(key)) {
                ToastUtils.showShort("请输入要查询的房号");
                return;
            }
            page = 1;
            list.clear();
            getMap(page, key);

        }
    }


    public void initAdapter() {

        adapter = new BaseQuickAdapter<IdVerificationBean.WanwanBean, BaseViewHolder>(R.layout.item_verification_list, list) {
            @Override
            protected void convert(BaseViewHolder helper, final IdVerificationBean.WanwanBean item) {
                TextView tx_verification_name = helper.itemView.findViewById(R.id.tx_verification_name);
                TextView tx_verification_look = helper.itemView.findViewById(R.id.tx_verification_look);
                TextView tx_verification_review = helper.itemView.findViewById(R.id.tx_verification_review);
                tx_verification_name.setText(item.getHouseNo());
                switch (item.getStatus()) {
                    case "Y":
                        tx_verification_review.setText("审核通过");
                        tx_verification_review.setBackground(getResources().getDrawable(R.drawable.shape_verification_btn_review));
                        tx_verification_look.setVisibility(View.GONE);
                        break;

                    case "D":
                        tx_verification_review.setText("待审核");
                        tx_verification_review.setBackground(getResources().getDrawable(R.drawable.shape_verification_btn_review));
                        tx_verification_look.setVisibility(View.GONE);
                        break;

                    case "N":
                        tx_verification_review.setText("审核未通过");
                        tx_verification_review.setBackground(getResources().getDrawable(R.drawable.shape_verification_btn_review));
                        tx_verification_review.setEnabled(item.getStatus().equals("N") ? true : false);
                        tx_verification_look.setVisibility(View.GONE);
                        break;
                }

                tx_verification_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(IdVerificationActivity.this, IdentityAuditActivity.class);
                        intent.putExtra("item", item);
                        startActivity(intent);
                    }
                });

                tx_verification_review.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(IdVerificationActivity.this, IdentityAuditActivity.class);
                        intent.putExtra("item", item);
                        startActivity(intent);
                    }
                });
            }
        };
        verification_list.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        dimissDialog();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        smart_layout.finishRefresh(1000);
        list.clear();
        page = 1;
        getMap(page, key);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        smart_layout.finishLoadmore(1000);
        page++;
        getMap(page, key);
    }

    @Override
    public void onAuList(List<IdVerificationBean.WanwanBean> auList) {
        list.addAll(auList);
        adapter.notifyDataSetChanged();
    }
}
