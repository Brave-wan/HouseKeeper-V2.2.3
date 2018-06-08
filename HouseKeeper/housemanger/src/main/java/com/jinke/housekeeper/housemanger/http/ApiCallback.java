package com.jinke.housekeeper.housemanger.http;

import android.text.TextUtils;

import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import com.jinke.housekeeper.housemanger.config.SignHousing;
import www.jinke.com.library.utils.NetWorksUtils;

/**
 * 作者：Rance on 2016/11/18 15:19
 * 邮箱：rance935@163.com
 */
public abstract class ApiCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(HousingResult result);


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        HousingResult errorMessageInfo = new HousingResult();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            errorMessageInfo.setErrmsg(httpException.getMessage());
            errorMessageInfo.setErrcode(String.valueOf(code));
        } else if (e instanceof SocketTimeoutException) {
            int code = 1000;
            errorMessageInfo.setErrmsg("连接超时");
            errorMessageInfo.setErrcode(String.valueOf(code));
        }
        if (!NetWorksUtils.isConnected(SignHousing.mContext)) {
            errorMessageInfo.setErrmsg("请检查网络连接");
        }
        if (!TextUtils.isEmpty(errorMessageInfo.getErrmsg())) {
            onFailure(errorMessageInfo);
        }
    }

    @Override
    public void onNext(M model) {
        String code = ((HousingResult) model).getErrcode() + "";
        HousingResult baseResult = new HousingResult();
        switch (Integer.valueOf(code)) {
            case 200:
                onSuccess(model);
                break;
            default:
                baseResult.setErrcode(code);
                baseResult.setErrmsg(((HousingResult) model).getErrmsg());
                onFailure(baseResult);
                break;
        }
    }

    @Override
    public void onCompleted() {
    }

}
