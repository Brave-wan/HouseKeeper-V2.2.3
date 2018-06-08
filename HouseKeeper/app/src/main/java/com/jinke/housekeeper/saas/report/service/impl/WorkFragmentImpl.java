package com.jinke.housekeeper.saas.report.service.impl;

import android.content.Context;
import android.util.Log;

import com.jinke.housekeeper.R;
import com.jinke.housekeeper.saas.report.http.HttpMethods;
import com.jinke.housekeeper.saas.report.service.WorkFragmentBiz;
import com.jinke.housekeeper.saas.report.service.listener.WorkFragmentListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/20.
 */

public class WorkFragmentImpl implements WorkFragmentBiz {
    private Context mContext;

    public WorkFragmentImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getappHandleData(RequestParams params, final WorkFragmentListener listener) {
        HttpUtils http = new HttpUtils(200000);

        http.send(HttpRequest.HttpMethod.POST,
                HttpMethods.BASE_URL + "Appinterface/appHandle/",
                params, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.i("wan", "responseInfo-----" + responseInfo.result);
                        try {
                            String code;
                            JSONObject jsonObject = new JSONObject(responseInfo.result);
                            String errorMsg = jsonObject.getString("errmsg");
                            code = jsonObject.getString("errcode");
                            if (code.equals("1"))
                                listener.getappHandleDataSuccess();
                            else
                                listener.getappHandleDataFailure(errorMsg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        listener.getappHandleDataFailure(mContext.getString(R.string.fragment_report_commit_failue));
                    }
                });
    }
}
