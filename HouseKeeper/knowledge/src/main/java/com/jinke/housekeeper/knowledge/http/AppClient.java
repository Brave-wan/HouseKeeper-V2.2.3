package com.jinke.housekeeper.knowledge.http;


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

/**
 * 作者：Rance on 2016/11/18 15:19
 * 邮箱：rance935@163.com
 */
public class AppClient {
    public static Retrofit mRetrofit;
    private static OkHttpClient okHttpClient;
    public final static String Base_URL = UrlConfig.getAppUrl();
    public static final String CSLIB_URL = "http://dev-oa.tq-service.com";//公告正式

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .baseUrl(Base_URL)
                    .build();
        }
        return mRetrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (true) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //添加头文件

                builder.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request().newBuilder()
                                .addHeader("uid", KnowledgeSign.getUserId())
                                .build();
                        return chain.proceed(originalRequest);
                    }
                });
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
                builder.addInterceptor(new LogInterceptor());
            }
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

}
