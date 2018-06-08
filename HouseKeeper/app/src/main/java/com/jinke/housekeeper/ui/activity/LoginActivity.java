package com.jinke.housekeeper.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jinke.housekeeper.R;
import com.jinke.housekeeper.base.BaseActivity;
import com.jinke.housekeeper.db.DBUserUtils;

import www.jinke.com.library.db.LoginInfo;
import www.jinke.com.library.db.SessionInfo;

import com.jinke.housekeeper.presenter.LoginActivityPresenter;
import com.jinke.housekeeper.saas.report.util.KeyBoardUtils;
import com.jinke.housekeeper.utils.CommonlyUtils;
import com.jinke.housekeeper.view.LoginActivityView;

import java.util.SortedMap;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by 32 on 2016/12/21.
 */

public class LoginActivity extends BaseActivity<LoginActivityView, LoginActivityPresenter> implements View.OnClickListener, LoginActivityView {
    @Bind(R.id.et_login_username)
    EditText etLoginUsername;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.tv_errortips_login)
    TextView errorTips;

    private SessionInfo userInfo;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        initUser();
    }

    @Override
    public LoginActivityPresenter initPresenter() {
        return new LoginActivityPresenter(this);
    }

    @OnClick({R.id.et_login_pwd, R.id.activity_login_button_login, R.id.activity_login_button_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_login_pwd:
                errorTips.setVisibility(View.INVISIBLE);
                break;
            case R.id.activity_login_button_login:
                loginCheck();
                break;
            case R.id.activity_login_button_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void initUser() {
        userInfo = CommonlyUtils.getSessionInfo(LoginActivity.this);
        if (userInfo != null) {
            etLoginUsername.setText(userInfo.getUserName());
            etLoginPwd.setText(userInfo.getPassword());
        }
        etLoginPwd.setOnClickListener(this);
    }

    public void keyboard(View view) {
        KeyBoardUtils.closeKeybord(etLoginUsername, LoginActivity.this);
        KeyBoardUtils.closeKeybord(etLoginPwd, LoginActivity.this);
    }

    public void getUserLoginData() {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("userName", etLoginUsername.getText().toString().trim());
        map.put("passWord", etLoginPwd.getText().toString().trim());
        presenter.getUserLoginData(map);
    }

    @Override
    public void getUserLoginDataonNext(LoginInfo info) {
        if (info.getName().length() > 0) {
            Intent intent = new Intent(LoginActivity.this, MainsActivity.class);
            intent.putExtra("info", info);
            startActivity(intent);
            finish();
            JPushInterface.setAlias(LoginActivity.this, info.getUserId() + "", null);
            //保存用户基础数据
            DBUserUtils.saveLoginInfo(this, info, etLoginPwd, etLoginUsername);
            //获取报事token
            presenter.getAppOauth("SE3Brc18CG0r44QTed1A");
        }
    }

    @Override
    public void getUserLoginDataonError(String code, String msg) {
        errorTips.setText("*" + msg);
        errorTips.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.doNext(this, requestCode, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 2);
        }
    }

    private void loginCheck() {
        if (etLoginUsername.getText().toString().trim().length() == 0) {
            ToastUtils.showShort(getString(R.string.activity_login_input_user_name));
            return;
        }
        if (etLoginPwd.getText().toString().trim().length() < 6) {
            ToastUtils.showShort(getString(R.string.activity_login_input_error_password));
            return;
        }
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUserName(etLoginUsername.getText().toString().trim());
        sessionInfo.setPassword(etLoginPwd.getText().toString().trim());
        CommonlyUtils.saveSession(LoginActivity.this, sessionInfo);
        getUserLoginData();
    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        dimissDialog();

    }
}
