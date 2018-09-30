package com.li.net.proxy.impl;

import com.li.net.callback.ICallBack;
import com.li.net.proxy.IHttpProxy;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author li
 * @since 2018/9/29
 */
public class RetrofitProcessor implements IHttpProxy {


    @Override
    public void get(String url, Map<String, String> param, Object tag, ICallBack callBack) {

    }

    @Override
    public void post(String url, Map<String, String> param, Object tag, ICallBack callBack) {

    }

    @Override
    public void download(String url, Map<String, String> param, String filePath, Object tag, ICallBack callBack) {

    }

    @Override
    public void cancel(Object tag) {
    }


    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 30;
    private Retrofit retrofit;

    private RetrofitProcessor(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    private static final String BASE_URL = "https://api.douban.com/v2/movie/";

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
