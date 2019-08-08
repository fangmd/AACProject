package com.passon.aacproject.repository;

import com.passon.aacproject.entity.User;
import com.passon.aacproject.service.BaseNetDisableObserver;
import com.passon.netlib.BaseResp;

import io.reactivex.Single;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午3:21
 * Description: 单例 用户信息 获取 处理 类
 */
public class UserRepository implements IUserRepository {


    private final IUserRepository mInstance;

    public UserRepository() {
        mInstance = UserRepositoryRetrofit.getInstance();
    }

    @Override
    public void getUserInfo(BaseNetDisableObserver<BaseResp<User>> observer) {
        mInstance.getUserInfo(observer);
    }


    public Single<BaseResp<User>> getUserInfo2() {
        return mInstance.getUserInfo2();
    }

}
