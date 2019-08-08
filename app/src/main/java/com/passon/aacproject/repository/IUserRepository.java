package com.passon.aacproject.repository;

import com.passon.aacproject.entity.User;
import com.passon.aacproject.service.BaseNetDisableObserver;
import com.passon.netlib.BaseResp;

import io.reactivex.Single;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午3:21
 * Description:
 */
public interface IUserRepository {

    void getUserInfo(BaseNetDisableObserver<BaseResp<User>> observer);

    Single<BaseResp<User>> getUserInfo2();

}
