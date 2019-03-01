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

    /**
     * 普通请求用 OkHttpClient
     *
     * @param clean false: 表示需要添加请求拦截器，true: 表示不添加请求拦截器,请求不加请求头等自定义信息
     * @return OkHttpClient
     */
    private static OkHttpClient getOkHttpClient(boolean clean) {
        //setup cache
        File httpCacheDirectory = new File(App.sInstance.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addNetworkInterceptor(cacheInterceptor); //缓存拦截器

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);//日志拦截器
        }

        if (!clean) {
            builder.addInterceptor(requestInterceptor);
        }

        return builder.connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)//time out
                .addInterceptor(sNetWorkInterceptor)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//读超时
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//写超时
                .cache(cache)
                .proxySelector(new MyProxySelector())
                .retryOnConnectionFailure(true).build();//失败重连
    }

    /**
     * 下载用 OkHttpClient
     *
     * @param clean false: 表示需要添加请求拦截器，true: 表示请求不加请求头等自定义信息
     * @return OkHttpClient
     */
    private static OkHttpClient getDownlaodOkHttpClient(boolean clean) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            builder.addInterceptor(loggingInterceptor);//日志拦截器
        }

        if (!clean) {
            builder.addInterceptor(requestInterceptor);
        }

        return builder.connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)//time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//读超时
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//写超时
                .proxySelector(new MyProxySelector())
                .retryOnConnectionFailure(true).build();//失败重连
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
        retrofitService = getRetrofit(url, false).create(clazz);
        retrofitServices.put(clazz.getName() + url, retrofitService);
        return retrofitService;
    }

    /**
     * 获取 Retrofit
     *
     * @param url base url
     * @return Retrofit
     */
    public static Retrofit getRetrofit(String url, boolean clean) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient(clean))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 获取 下载 用的 Retrofit
     *
     * @param clean false: 表示需要添加请求拦截器，true: 表示请求不加请求头等自定义信息
     * @return Retrofit
     */
    public static Retrofit getDownlaodRetrofit(boolean clean) {
        return new Retrofit.Builder()
                .baseUrl("http://black")
                .client(getDownlaodOkHttpClient(clean))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
