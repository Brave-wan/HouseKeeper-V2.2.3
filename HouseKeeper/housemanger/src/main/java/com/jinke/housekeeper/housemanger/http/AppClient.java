package com.jinke.housekeeper.housemanger.http;


import android.util.Log;

import com.jinke.housekeeper.housemanger.config.SignHousing;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import www.jinke.com.library.Config.UrlConfig;
import www.jinke.com.library.utils.SharedPreferencesUtils;

/**
 * 作者：Rance on 2016/11/18 15:19
 * 邮箱：rance935@163.com
 */
public class AppClient {
    public static Retrofit mRetrofit;
    private static OkHttpClient okHttpClient;

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            Log.i("jiefang",""+UrlConfig.getHousing());
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(UrlConfig.getHousing())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("uid", SignHousing.getUserId())
                            .build();
                    return chain.proceed(request);
                }
            });
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
            builder.addInterceptor(new LogInterceptor());
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

}
