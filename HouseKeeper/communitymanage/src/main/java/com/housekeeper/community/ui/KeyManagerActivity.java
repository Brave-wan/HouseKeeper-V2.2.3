package com.housekeeper.community.ui;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.housekeeper.community.R;
import com.housekeeper.community.bean.KeyManagerListBean;
import com.housekeeper.community.ui.activity.QRCodeActivity;
import com.housekeeper.community.ui.activity.ReturnKeyActivity;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-22.
 */

public class KeyManagerActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    RecyclerView rv_key_manager;
    List<KeyManagerListBean> list = new ArrayList<>();
    BaseQuickAdapter<KeyManagerListBean, BaseViewHolder> adapter;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_key_manager;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.community_key_manager_title));
        showBackwardViewIco(R.mipmap.back_image);
        rv_key_manager = findViewById(R.id.rv_key_manager);
        initAdapter();
    }

    private void initAdapter() {
        list.add(new KeyManagerListBean("借钥匙", R.drawable.icon_key_manage_hosting));
        list.add(new KeyManagerListBean("还钥匙", R.drawable.icon_key_manager_return));
        rv_key_manager.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new BaseQuickAdapter<KeyManagerListBean, BaseViewHolder>(R.layout.item_key_manager_list, list) {
            @Override
            protected void convert(BaseViewHolder helper, KeyManagerListBean item) {
                ImageView item_workbench_image = helper.itemView.findViewById(R.id.item_workbench_image);
                TextView item_workbench_text = helper.itemView.findViewById(R.id.item_workbench_text);
                item_workbench_text.setText(item.getApplicationName());
                item_workbench_image.setBackground(getResources().getDrawable(item.getApplicationIcon()));
            }
        };
        rv_key_manager.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    private int REQUEST_CODE = 0x122;

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            //借钥匙
            case 0:
//                startActivity(new Intent(this, BorrowKeyActivity.class));
//                Intent intent = new Intent(this, CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
                startActivity(new Intent(this, QRCodeActivity.class));
                break;
            //还钥匙
            case 1:
                startActivity(new Intent(this, ReturnKeyActivity.class));
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
        }
    }


}
