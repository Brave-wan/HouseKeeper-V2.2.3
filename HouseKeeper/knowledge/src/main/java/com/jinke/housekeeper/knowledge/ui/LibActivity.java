package com.jinke.housekeeper.knowledge.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jinke.housekeeper.knowledge.R;
import com.jinke.housekeeper.knowledge.adapter.LibStandardsAdapter;
import com.jinke.housekeeper.knowledge.bean.KnowledgeInfo;
import com.jinke.housekeeper.knowledge.prsent.LibActivityPresenter;
import com.jinke.housekeeper.knowledge.view.LibActivityView;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.utils.SharedPreferencesUtils;
import www.jinke.com.library.widget.LoadingLayout;
import www.jinke.com.library.widget.RecycleViewDivider;

/**
 * Created by Administrator on 2017/8/29.
 */

public class LibActivity extends BaseActivity<LibActivityView, LibActivityPresenter> implements
        LibActivityView, LibStandardsAdapter.OnItemClickLitener {
    RecyclerView rv_lib_list;
    private List<KnowledgeInfo.ListObjBean> list = new ArrayList<>();
    private LibStandardsAdapter libAdapter;
    private String sessionId = SharedPreferencesUtils.init(LibActivity.this).getString("sessionId");
    private String userId = SharedPreferencesUtils.init(LibActivity.this).getString("userId");
    private LoadingLayout loading_layout;

    @Override
    public LibActivityPresenter initPresenter() {

        return new LibActivityPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_library;
    }

    @Override
    protected void initView() {
        initTitle();
        initData();
        getLore();
    }

    private void initData() {
        loading_layout.setStatus(LoadingLayout.Loading);
        rv_lib_list.setLayoutManager(new LinearLayoutManager(this));
        rv_lib_list.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 0, getResources().getColor(R.color.activity_main_default_bg_color)));
        libAdapter = new LibStandardsAdapter(list, this);
        libAdapter.setOnItemClickLitener(this);
        rv_lib_list.setAdapter(libAdapter);
    }


    private void initTitle() {
        showBackwardViewIco(R.mipmap.back_image);
        rv_lib_list = findViewById(R.id.rv_lib_list);
        loading_layout = findViewById(R.id.loading_layout);
        setTitle(getResources().getString(R.string.knowledge_lib_page_title));
    }

    public void getLore() {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("sessionId", sessionId);
        map.put("userId", userId);
        presenter.getLore(map);
    }

    @Override
    public void getLoreOnNext(KnowledgeInfo info) {
        libAdapter.setData(info.getListObj());
        loading_layout.setStatus(info.getListObj().size() > 0 ? LoadingLayout.Success : LoadingLayout.Empty);
    }

    @Override
    public void onItemClick(String id) {
        Intent intent = new Intent(LibActivity.this, LibAllActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("sessionId", sessionId);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }
}
