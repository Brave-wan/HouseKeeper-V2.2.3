package com.jinke.housekeeper.housemanger.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jinke.housekeeper.housemanger.R;
import com.jinke.housekeeper.housemanger.bean.IdVerificationBean;
import com.jinke.housekeeper.housemanger.bean.VerificationDetailsBean;
import com.jinke.housekeeper.housemanger.present.IdentityAuditPresent;
import com.jinke.housekeeper.housemanger.view.IdentityAuditView;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.ui.ImagePagerActivity;
import www.jinke.com.library.widget.CustomPopWindows;

/**
 * Created by root on 18-5-15.
 * 身份审核
 */

public class IdentityAuditActivity extends BaseActivity<IdentityAuditView, IdentityAuditPresent> implements AdapterView.OnItemSelectedListener, View.OnClickListener, IdentityAuditView {
    RecyclerView rv_entity_audit_list;
    TextView tx_id_entity_agree;
    TextView tx_id_entity_refuse;
    LinearLayout ll_id_entity_bottom;
    BaseQuickAdapter<VerificationDetailsBean.DataListBean, BaseViewHolder> adapter;
    List<VerificationDetailsBean.DataListBean> list = new ArrayList<>();
    IdVerificationBean.WanwanBean item;

    @Override
    public IdentityAuditPresent initPresenter() {
        return new IdentityAuditPresent(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_id_entity_audit;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.housing_manager_entity_audit_title));
        tx_id_entity_agree = findViewById(R.id.tx_id_entity_agree);
        tx_id_entity_refuse = findViewById(R.id.tx_id_entity_refuse);
        ll_id_entity_bottom = findViewById(R.id.ll_id_entity_bottom);
        item = (IdVerificationBean.WanwanBean) getIntent().getSerializableExtra("item");
        showBackwardViewIco(R.drawable.icon_housing_manager_back);
        rv_entity_audit_list = findViewById(R.id.rv_entity_audit_list);
        tx_id_entity_agree.setOnClickListener(this);
        tx_id_entity_refuse.setOnClickListener(this);
        presenter.getQueryOwnerCheck(item.getId());
        ll_id_entity_bottom.setVisibility(item.getStatus().equals("Y") ? View.GONE : View.VISIBLE);
        initAdapter();
    }


    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_entity_audit_list.setLayoutManager(linearLayoutManager);
        adapter = new BaseQuickAdapter<VerificationDetailsBean.DataListBean, BaseViewHolder>(R.layout.item_entity_audit_list, list) {
            @Override
            protected void convert(BaseViewHolder helper, final VerificationDetailsBean.DataListBean item) {
                TextView tx_audit_room_number = helper.itemView.findViewById(R.id.tx_audit_room_number);//房号
                TextView tx_audit_owner_name = helper.itemView.findViewById(R.id.tx_audit_owner_name);//业主姓名
                TextView tx_contract_zip_code = helper.itemView.findViewById(R.id.tx_contract_zip_code);//身份证号码
                TextView tx_contract_phone = helper.itemView.findViewById(R.id.tx_contract_phone);
                ImageView img_audit_anti = helper.itemView.findViewById(R.id.img_audit_anti);//方面
                ImageView img_audit_positive = helper.itemView.findViewById(R.id.img_audit_positive);//正面
                tx_contract_phone.setText(item.getPhone());
                tx_audit_owner_name.setText(item.getOwnerName());
                tx_audit_room_number.setText(item.getHouseNo());
                tx_contract_zip_code.setText(item.getIdCard());

                img_audit_anti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<String> tempList = new ArrayList<>();
                        tempList.add(item.getImage2());
                        tempList.add(item.getImage1());
                        ImagePagerActivity.startActivity(IdentityAuditActivity.this, tempList, 0);
                    }
                });

                img_audit_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<String> tempList = new ArrayList<>();
                        tempList.add(item.getImage2());
                        tempList.add(item.getImage1());
                        ImagePagerActivity.startActivity(IdentityAuditActivity.this, tempList, 0);
                    }
                });


                Glide.with(IdentityAuditActivity.this).
                        load(item.getImage1())
                        .error(www.jinke.com.library.R.drawable.icon_simal)
                        .placeholder(www.jinke.com.library.R.drawable.icon_simal)
                        .into(img_audit_anti);

                Glide.with(IdentityAuditActivity.this).
                        load(item.getImage2())
                        .error(www.jinke.com.library.R.drawable.icon_simal)
                        .placeholder(www.jinke.com.library.R.drawable.icon_simal)
                        .into(img_audit_positive);

            }
        };
        rv_entity_audit_list.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        //拒绝审核
        if (v.getId() == R.id.tx_id_entity_refuse) {
            getExaminationRefuse(v);
            //同意审核
        } else if (v.getId() == R.id.tx_id_entity_agree) {
            getCustomPopWindows(v);
        } else if (v.getId() == R.id.tx_identity_cancel) {
            windows.dismiss();
            //通过审核确定按钮
        } else if (v.getId() == R.id.tx_identity_sure) {
            presenter.getCheckOwner(item.getId(), "", "Y");
            //通过审核取消按钮
        } else if (v.getId() == R.id.tx_passed_sure) {
            windows.dismiss();
            finish();
            //不通过审核取消按钮
        } else if (v.getId() == R.id.tx_refuse_cancel) {
            windows.dismiss();
            //不通过审核确定按钮
        } else if (v.getId() == R.id.tx_refuse_sure) {
            presenter.getCheckOwner(item.getId(), remark, "N");
        }
    }

    CustomPopWindows windows;
    TextView tx_identity_cancel;
    TextView tx_identity_sure;
    TextView tx_title_content;

    /**
     * @param v
     */
    public void getCustomPopWindows(View v) {
        windows = new CustomPopWindows(this);
        windows.setContentView(View.inflate(this, R.layout.dialog_identity_audit, null));
        View view = windows.getContentView();
        tx_identity_cancel = view.findViewById(R.id.tx_identity_cancel);
        tx_identity_sure = view.findViewById(R.id.tx_identity_sure);
        tx_title_content = view.findViewById(R.id.tx_title_content);
        windows.showAtLocation(v, Gravity.CENTER, 0, 0);
        tx_identity_sure.setOnClickListener(this);
        tx_identity_cancel.setOnClickListener(this);
    }

    TextView tx_passed_sure;

    /**
     * 审核通过
     *
     * @param v
     */
    public void getExaminationPassed(View v) {
        windows = new CustomPopWindows(this);
        windows.setContentView(View.inflate(this, R.layout.dialog_identity_sure, null));
        View view = windows.getContentView();
        tx_passed_sure = view.findViewById(R.id.tx_passed_sure);
        windows.showAtLocation(v, Gravity.CENTER, 0, 0);
        tx_passed_sure.setOnClickListener(this);
    }

    Spinner sp_refuse_reason;
    TextView tx_refuse_sure;
    TextView tx_refuse_cancel;

    /**
     * 审核不通过
     *
     * @param v
     */
    public void getExaminationRefuse(View v) {
        windows = new CustomPopWindows(this);
        windows.setContentView(View.inflate(this, R.layout.dialog_identity_refuse, null));
        View view = windows.getContentView();
        tx_refuse_sure = view.findViewById(R.id.tx_refuse_sure);
        sp_refuse_reason = view.findViewById(R.id.sp_refuse_reason);
        windows.showAtLocation(v, Gravity.CENTER, 0, 0);
        tx_refuse_cancel = view.findViewById(R.id.tx_refuse_cancel);
        tx_refuse_sure.setOnClickListener(this);
        tx_refuse_cancel.setOnClickListener(this);
        sp_refuse_reason.setOnItemSelectedListener(this);
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
    public void onVerificationDetailsBean(VerificationDetailsBean bean) {
        list.addAll(bean.getDataList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckOwner(String status) {
        if (status.equals("N")) {
            finish();
        } else {
            getExaminationPassed(layout_titlebar);
        }
    }

    String remark = "";


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] languages = getResources().getStringArray(R.array.reason);
        remark = languages[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
