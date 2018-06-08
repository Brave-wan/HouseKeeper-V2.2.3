package com.jinke.housekeeper.saas.report.ui.fragment.avtivitycommit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.jinke.housekeeper.R;
import com.jinke.housekeeper.saas.report.ui.adapter.SelfHistoryAdapter;
import com.jinke.housekeeper.applicaption.MyApplication;
import com.jinke.housekeeper.base.BaseFragmentV4;
import com.jinke.housekeeper.saas.report.bean.ProcessDetailBean;
import com.jinke.housekeeper.saas.report.bean.SelfHistoryBean;
import www.jinke.com.library.db.UserInfo;
import com.jinke.housekeeper.saas.report.bean.WaitRectifiedBean;
import com.jinke.housekeeper.saas.report.presenter.PersonTodayFragmentPresenter;
import com.jinke.housekeeper.saas.report.ui.activity.fragmentworkbench.RectProcessActivity;
import com.jinke.housekeeper.saas.report.ui.widget.RecycleViewDivider;
import www.jinke.com.library.utils.SharedPreferencesUtils;

import com.jinke.housekeeper.saas.report.util.ToastUtils;
import com.jinke.housekeeper.saas.report.view.PersonTodayFragmentView;
import com.jinke.housekeeper.ui.widget.LoadingLayout;
import com.jinke.housekeeper.utils.CommonlyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import butterknife.Bind;
import www.jinke.com.library.utils.SingleLogin;

/**
 * Created by pc on 2017/3/15.
 */

public class PersonTodayFragment extends BaseFragmentV4<PersonTodayFragmentView, PersonTodayFragmentPresenter> implements
        View.OnClickListener, SelfHistoryAdapter.OnItemClickListener,
        OnRefreshListener, OnLoadmoreListener,
        LoadingLayout.OnReloadListener,
        PersonTodayFragmentView {
    @Bind(R.id.loadLayout)
    LoadingLayout loadLayout;
    @Bind(R.id.scrollView)
    SmartRefreshLayout scrollView;
    @Bind(R.id.fillListView)
    RecyclerView allListView;
    private UserInfo userInfo;

    private WaitRectifiedBean.ListObjBean waitBean; //发送给流程节点整改数据类
    private ProcessDetailBean.ObjBean objBeanDetail = new ProcessDetailBean.ObjBean();//节点详情传递给流程节点上端的数据类

    private SelfHistoryAdapter selfHistoryAdapter;
    private List<SelfHistoryBean.ListObjBean> selfHistoryList = new ArrayList();

    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_today;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        loadLayout.setStatus(isConnected ? LoadingLayout.Loading : LoadingLayout.No_Network);
        scrollView.setOnRefreshListener(this);
        allListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allListView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.home_line)));
        scrollView.setOnLoadmoreListener(this);
        scrollView.setOnClickListener(this);
        loadLayout.setOnReloadListener(this);
        userInfo = CommonlyUtils.getUserInfo(getActivity());
        selfHistoryAdapter = new SelfHistoryAdapter(getActivity(), selfHistoryList, R.layout.item_wait_rectified);
        allListView.setAdapter(selfHistoryAdapter);
        selfHistoryAdapter.setOnItemClickListener(this);
    }


    String startTime = "";
    String endTime = "";

    private void getTime() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 0);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        startTime = dateString.toString().trim();
        endTime = dateString.toString().trim();
        LogUtils.i("wanTime", startTime);
        LogUtils.i("wanTime", endTime);

    }

    private void getSelfHistoryList(int pageInfo) {
        getTime();
        SortedMap<String, String> map = new TreeMap<>();
        map.put("sessionId", SharedPreferencesUtils.init(getActivity()).getString("quality_sessionId"));
        map.put("userId", MyApplication.getUserId());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("pageInfo", CommonlyUtils.pageInfo(pageInfo));
        presenter.getSelfHistoryList(map);
    }


    @Override
    public void getSelfHistoryListonNext(SelfHistoryBean bean) {
        if (bean != null) {
            selfHistoryList.addAll(bean.getListObj());
            selfHistoryAdapter.setData(selfHistoryList);
        }
        loadLayout.setStatus(selfHistoryList.size() > 0 ? LoadingLayout.Success : LoadingLayout.Empty);
    }

    @Override
    public void getSelfHistoryListonError(String code, String msg) {
        ToastUtils.showShort(getActivity(),msg);
        SingleLogin.errorState(getActivity(), code);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onReload(View v) {
        CommonlyUtils.setLoadConnected(loadLayout, this.getActivity());
        selfHistoryList.clear();
        page = 1;
        getSelfHistoryList(page);
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(1000);
        selfHistoryList.clear();
        page = 1;
        getSelfHistoryList(page);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore(1000);
        page++;
        getSelfHistoryList(page);
    }

    @Override
    public void onResume() {
        super.onResume();
        selfHistoryList.clear();
        getSelfHistoryList(page);
    }

    @Override
    public PersonTodayFragmentPresenter initPresenter() {
        return new PersonTodayFragmentPresenter(getActivity());
    }


    @Override
    public void onItemClick(SelfHistoryBean.ListObjBean selfHistoryBean) {
        waitBean = new WaitRectifiedBean.ListObjBean();
        waitBean.setId(String.valueOf(selfHistoryBean.getId()));//类型的强制转化
        waitBean.setIsState(String.valueOf(selfHistoryBean.getIsState()));
        Intent intent = new Intent(getContext(), RectProcessActivity.class);
        if (selfHistoryBean != null) {
            objBeanDetail.setHandUserName(selfHistoryBean.getSuperviseName());
            objBeanDetail.setSceneName(selfHistoryBean.getSceneName());
            objBeanDetail.setAreaName(selfHistoryBean.getAreaName());
            if (selfHistoryBean.getAudioaddress() != null || selfHistoryBean.getAudioaddress() != "") {
                objBeanDetail.setAudioAdd(selfHistoryBean.getAudioaddress());
                objBeanDetail.setAudioAddLen(selfHistoryBean.getAudiolen());

            }
            if (selfHistoryBean.getDescribe() != null || selfHistoryBean.getDescribe() != "") {
                objBeanDetail.setSuperviseDescribe(selfHistoryBean.getDescribe());

            }
        }
        intent.putExtra("objBeanDetail", objBeanDetail);
        intent.putExtra("waitBean", waitBean);
        startActivity(intent);
    }

}