package com.jinke.housekeeper.knowledge.ui;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.jinke.housekeeper.knowledge.R;
import com.jinke.housekeeper.knowledge.adapter.LibAllAdapter;
import com.jinke.housekeeper.knowledge.bean.LibAllInfo;
import com.jinke.housekeeper.knowledge.prsent.LibAllActivityPresenter;
import com.jinke.housekeeper.knowledge.view.LibAllActivityView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import www.jinke.com.library.Config.CommonConfig;
import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.widget.CustomGridView;
import www.jinke.com.library.widget.LoadingLayout;


/**
 * Created by 32 on 2017/1/5.
 */

public class LibAllActivity extends BaseActivity<LibAllActivityView, LibAllActivityPresenter> implements
        LibAllAdapter.OnItemClickLitener, OnLoadmoreListener, OnRefreshListener,
        View.OnClickListener, LibAllActivityView {
    SmartRefreshLayout smart_layout;
    EditText searchInput;
    private LibAllAdapter allAdapter;
    private String sessionId;

    CustomGridView rvLibAll;
    private LoadingLayout loding_layout;
    private String id = "";
    private int page = 1;
    private List<LibAllInfo.ListObjBean> arrayListLibAll = new ArrayList<>();
    private String userId;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_knowledge_lib_all;
    }

    @Override
    protected void initView() {
        showBackwardViewIco(R.mipmap.back_image);
        setTitle(getResources().getString(R.string.knowledge_lib_page_title));
        findById();
    }

    private void findById() {
        id = getIntent().getStringExtra("id");
        sessionId = getIntent().getStringExtra("sessionId");
        userId = getIntent().getStringExtra("userId");
        rvLibAll = findViewById(R.id.rv_lib_all);
        searchInput = findViewById(R.id.search_input);
        smart_layout = findViewById(R.id.smart_layout);
        loding_layout = findViewById(R.id.loading_layout);
        smart_layout.setOnLoadmoreListener(this);
        smart_layout.setOnRefreshListener(this);
        initDate();
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    public LibAllActivityPresenter initPresenter() {
        return new LibAllActivityPresenter(this);
    }

    private void initDate() {
        searchInput.setHint("请输入关键字搜索");
        loding_layout.setStatus(LoadingLayout.Loading);
        searchInput.addTextChangedListener(textWatcher);
        allAdapter = new LibAllAdapter(arrayListLibAll, this);
        allAdapter.setOnItemClickLitener(this);
        rvLibAll.setAdapter(allAdapter);
        getScenePage(page);
    }

    public void getScenePage(int page) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("pageInfo", CommonConfig.pageInfo(page));
        if (searchInput.getText().toString().trim() != null) {
            map.put("query", searchInput.getText().toString().trim());
        }
        map.put("sceneId", id);
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter.getScenePage(map);
    }


    @Override
    public void onItemClick(String keyId) {
        Intent intent = new Intent(LibAllActivity.this, LibDetailsActivity.class);
        intent.putExtra("keyId", keyId);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scrollview) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        StatService.onResume(LibAllActivity.this);
//        StatService.trackBeginPage(LibAllActivity.this, "知识库");
    }


    @Override
    protected void onPause() {
        super.onPause();
//        StatService.onPause(LibAllActivity.this);
//        StatService.trackEndPage(LibAllActivity.this, "知识库");
    }


    private void searchInput() {
        if (searchInput.getText().toString().trim().length() >= 0) {
            page = 1;
            arrayListLibAll.clear();
            getScenePage(page);
        } else {
            ToastUtils.showShort("请输入关键字搜索");
        }
    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchInput();
        }
    };

    @Override
    public void getScenePageOnNext(LibAllInfo info) {
        if (info != null) {
            arrayListLibAll.addAll(info.getListObj());
            allAdapter.setData(arrayListLibAll);
            loding_layout.setStatus(arrayListLibAll.size() > 0 ? LoadingLayout.Success : LoadingLayout.Empty);
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        smart_layout.finishLoadmore(1000);
        page++;
        getScenePage(page);

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        smart_layout.finishRefresh(1000);
        page = 1;
        arrayListLibAll.clear();
        getScenePage(page);
    }
}
