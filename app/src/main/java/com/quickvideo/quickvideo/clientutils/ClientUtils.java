package com.quickvideo.quickvideo.clientutils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by powersen on 2017/12/5.
 * Rxjava结合retrofit的网络请求封装类
 */

public class ClientUtils {
    private Retrofit retrofit;

    private static class SingleInstance {
        private static final ClientUtils insTance = new ClientUtils(API.BASE_URL);
    }

    //使用默认的单例RetrofitManager，是默认的baseurl
    public static ClientUtils getDefault() {
        return SingleInstance.insTance;
    }

    private ClientUtils(String url) {
        this.retrofit = buildRetrofit(url);
    }

    //如果baseurl不是默认的话，那就调用这个方法构造一个新的RetrofitManager
    public static ClientUtils getclientData(String url) {
        return new ClientUtils(url);
    }
    //在这里初始化retrofit
    private Retrofit buildRetrofit(String url) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build();
    }

    public <T> T create(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
