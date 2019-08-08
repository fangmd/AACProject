package com.passon.aacproject.service;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午1:39
 * Description:
 */
public abstract class ApiC {

    public interface User {

        /** 获取用户信息 */
//        public static final String GET_INFO = "/user/info";
        String GET_INFO = "https://raw.githubusercontent.com/fangmd/fangmd.github.io/master/user";

    }

    public interface Invest {
        String INVEST = "/invest";
    }

}
