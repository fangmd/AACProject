package com.passon.aacproject;

/**
 * Author: Created by fangmingdong on 2018/8/29-下午5:42
 * Description:
 */
public abstract class C {

    // 通过 gradle 动态设置 base_url, BuildConfig.API_URL
//    public static final String BASE_URL = "https://api.com";


    public static final String EXTRA_DATA = "extra_data";


    /**
     * sharePreference Key
     */
    public interface Key {
    }


    public interface ErrorCode {

        int UNKNOWN = 400;

    }

    public interface ErrorMsg {

        /** 无网络时，提示信息 */
        public static final String ERROR_NETWORK = "网络不给力,请检查网络配置";

        /** token过期 */
        public static final String SESSION_INVALID = "session失效";

    }


    public interface Tag {

    }

}
