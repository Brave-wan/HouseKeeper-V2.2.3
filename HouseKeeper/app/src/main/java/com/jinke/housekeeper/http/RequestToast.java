package com.jinke.housekeeper.http;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.jinke.housekeeper.applicaption.MyApplication;

/**
 * Created by root on 5/01/17.
 */

public class RequestToast extends Handler {
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        HttpResult httpResult= (HttpResult) msg.obj;
        Toast.makeText(MyApplication.getInstance(),httpResult.getErrmsg(),Toast.LENGTH_SHORT).show();
    }
}
