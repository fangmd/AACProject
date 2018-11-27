package com.passon.aacproject.service.base;


import com.passon.aacproject.App;
import com.passon.aacproject.BuildConfig;
import com.passon.aacproject.C;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 构建OkHttpClient
 */

public class RxService {

    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private static RequestInterceptor requestInterceptor = new RequestInterceptor();
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private static CacheInterceptor cacheInterceptor = new CacheInterceptor();
    private static NetWorkInterceptor sNetWorkInterceptor = new NetWorkInterceptor();

    /** retrofit service缓存 */
    private static Map<String, Object> retrofitServices = new HashMap<>();
    private static Retrofit sRetrofit;

    /**
     * 获取OkHttp
     *
     * @return OkHttpClient
     */
    private static OkHttpClient getOkHttpClient() {
        return getOkHttpBuilder().build();
    }

    public static OkHttpClient.Builder getOkHttpBuilder() {
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        //setup cache
        File httpCacheDirectory = new File(App.sInstance.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(cacheInterceptor)//缓存拦截器
                .addInterceptor(requestInterceptor)//请求拦截器
                .addInterceptor(loggingInterceptor)//日志拦截器
                .addInterceptor(sNetWorkInterceptor)//日志拦截器
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)//time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//读超时
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//写超时
                .cache(cache)
                .retryOnConnectionFailure(true);//失败重连
    }

    public static <T> T createApi(Class<T> clazz) {
        return createApi(clazz, C.BASE_URL);
    }

    public synchronized static <T> T createApi(Class<T> clazz, String url) {
        T retrofitService;
        Object serviceObj = retrofitServices.get(clazz.getName() + url);
        if (serviceObj != null) {
            retrofitService = (T) serviceObj;
            return retrofitService;
        }
        retrofitService = getRetrofit(url).create(clazz);
        retrofitServices.put(clazz.getName() + url, retrofitService);
        return retrofitService;
    }

    /**
     * 获取Retrofit单例
     *
     * @param url base url
     * @return Retrofit
     */
    public static Retrofit getRetrofit(String url) {

        if (sRetrofit != null && C.BASE_URL.equals(url)) {
            return sRetrofit;
        }
        return sRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}

