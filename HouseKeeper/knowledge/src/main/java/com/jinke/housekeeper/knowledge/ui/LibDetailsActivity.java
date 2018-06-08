package com.jinke.housekeeper.knowledge.ui;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jinke.housekeeper.knowledge.R;
import com.jinke.housekeeper.knowledge.bean.LibDetailsInfo;
import com.jinke.housekeeper.knowledge.http.AppClient;
import com.jinke.housekeeper.knowledge.prsent.LibDetailsActivityPresenter;
import com.jinke.housekeeper.knowledge.view.LibDetailsActivityView;

import java.util.SortedMap;
import java.util.TreeMap;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.utils.SharedPreferencesUtils;
import www.jinke.com.library.widget.LoadingLayout;

/**
 * Created by root on 17-1-12.
 */

public class LibDetailsActivity extends BaseActivity<LibDetailsActivityView, LibDetailsActivityPresenter> implements
        View.OnClickListener, LibDetailsActivityView {
    WebView webview;
    TextView checkDetails;
    private String keyId;
    WebSettings settings;
    private LoadingLayout loading_layout;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_knowledge_lib_details;
    }

    @Override
    protected void initView() {
        showBackwardViewIco(R.mipmap.back_image);
        setTitle("详情");
        findById();
        keyId = getIntent().getStringExtra("keyId");
        checkDetails.setOnClickListener(this);
        settings = webview.getSettings();
        settings.setTextSize(WebSettings.TextSize.SMALLER);
        settings.setJavaScriptEnabled(true);
        getDetailsData();
    }

    private void findById() {
        loading_layout=findViewById(R.id.loading_layout);
        webview = findViewById(R.id.webview);
        checkDetails = findViewById(R.id.checkDetails);
        loading_layout.setStatus(LoadingLayout.Loading);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (loading_layout != null) {
                    loading_layout.setStatus(LoadingLayout.Success);
                }
            }
        });
    }

    @Override
    public LibDetailsActivityPresenter initPresenter() {
        return new LibDetailsActivityPresenter(this);
    }

    public void getDetailsData() {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("userId", SharedPreferencesUtils.init(this).getString("userId"));
        map.put("sessionId", SharedPreferencesUtils.init(this).getString("sessionId"));
        map.put("keyId", keyId);
        presenter.getDetailsData(map);
    }

//
//    @Override
//    public void getDetailsDataonError(String code, String msg) {
//        ToastUtils.showShort(msg);
////        SingleLogin.errorState(LibDetailsActivity.this, code);
//    }


    @Override
    public void onClick(View v) {
        //知识库
        Intent intent = new Intent();
        intent.setClassName("com.jinke.housekeeper", "com.jinke.housekeeper.saas.report.ui.activity.fragmentworkbench.ReportRegistActivity");

        intent.putExtra("inspType", "122");
        startActivity(intent);
        finish();
    }


    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        StatService.onResume(LibDetailsActivity.this);
//        StatService.trackBeginPage(LibDetailsActivity.this, "知识库详情页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        StatService.onPause(LibDetailsActivity.this);
//        StatService.trackEndPage(LibDetailsActivity.this, "知识库详情页面");
    }

    @Override
    public void getDetailsDataOnNext(LibDetailsInfo info) {
        webview.loadUrl(AppClient.CSLIB_URL + info.getObj().getHref());//测式
    }
}
