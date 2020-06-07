package org.ms.matrix.sdk.utils;



import org.ms.module.impl.utils.okhttp.OkHttpUtils;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 工具类
 */
public class RetrofitUtils {

    // 单例
    private static RetrofitUtils instance;


    public static RetrofitUtils getInstance() {

        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                instance = new RetrofitUtils();
            }
        }

        return instance;
    }

    // 缓冲
    private HashMap<String, Retrofit> cache = new HashMap<>();

    public Retrofit getRetrofitClient(String baseUrl) {

        Retrofit cacheRetrofit = cache.get(baseUrl);

        if (cacheRetrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.client(OkHttpUtils.getInstance());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            builder.baseUrl(baseUrl);
            Retrofit retrofit = builder.build();
            cache.put(baseUrl, retrofit);
            return retrofit;
        } else {
            return cacheRetrofit;
        }
    }

    private RetrofitUtils() {
    }
}