package com.jinke.housekeeper.knowledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jinke.housekeeper.knowledge.KnowledgeApplication;
import com.jinke.housekeeper.knowledge.R;
import com.jinke.housekeeper.knowledge.bean.KnowledgeApplicationBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.library.base.BaseFragmentV4;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by Administrator on 2017/7/27.
 * 新知识库
 */

public class KnowledgeFragment extends BaseFragmentV4 implements BaseQuickAdapter.OnItemClickListener {
    RecyclerView rv_knowledge_application_list;
    BaseQuickAdapter<KnowledgeApplicationBean, BaseViewHolder> adapter;
    List<KnowledgeApplicationBean> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_application_knowledge;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initTitle(view);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    private void initTitle(View view) {
        list.clear();
        list.add(new KnowledgeApplicationBean(R.drawable.fragment_workbench_quality_inspection, getString(R.string.fragment_knowledge_quality_inspection)));
        list.add(new KnowledgeApplicationBean(R.drawable.fragment_workbench_equipment_inspection, getString(R.string.fragment_knowledge_equipment_inspection)));
        list.add(new KnowledgeApplicationBean(R.drawable.icon_work_bench_equipment_maintenance, getString(R.string.fragment_knowledge_equipment_maintenance)));
        list.add(new KnowledgeApplicationBean(R.drawable.icon_equipment_project, getString(R.string.fragment_knowledge_equipment_project)));
        list.add(new KnowledgeApplicationBean(R.drawable.icon_equipment_work, getString(R.string.fragment_knowledge_equipment_work)));
        rv_knowledge_application_list = view.findViewById(R.id.rv_knowledge_application_list);
        setTitle(getActivity().getString(R.string.fragment_knowledge_title));
        initAdapter();
    }

    public void initAdapter() {
        rv_knowledge_application_list.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        adapter = new BaseQuickAdapter<KnowledgeApplicationBean, BaseViewHolder>(R.layout.item_knowledge_application, list) {
            @Override
            protected void convert(BaseViewHolder helper, KnowledgeApplicationBean item) {
                TextView title = helper.itemView.findViewById(R.id.item_workbench_text);
                ImageView image = helper.itemView.findViewById(R.id.item_workbench_image);
                Picasso.with(getActivity()).load(item.getRes()).into(image);
                title.setText(item.getDes());
            }
        };
        rv_knowledge_application_list.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), LibActivity.class));
                break;
            case 1:
                ToastUtils.showShort(getString(R.string.un_realized));
                break;
            case 2:
                ToastUtils.showShort(getString(R.string.un_realized));
                break;
            case 3:
                ToastUtils.showShort(getString(R.string.un_realized));
                break;
            case 4:
                ToastUtils.showShort(getString(R.string.un_realized));
                break;
        }
    }
}
