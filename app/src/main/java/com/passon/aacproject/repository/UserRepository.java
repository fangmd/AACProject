package com.passon.aacproject.repository;

import com.passon.aacproject.entity.BaseResponse;
import com.passon.aacproject.entity.User;
import com.passon.aacproject.service.base.ErrorHandleDisableObserver;

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
    public void getUserInfo(ErrorHandleDisableObserver<BaseResponse<User>> observer) {
        mInstance.getUserInfo(observer);
    }

}
