package com.passon.aacproject.repository;

import com.passon.aacproject.entity.BaseResponse;
import com.passon.aacproject.entity.User;
import com.passon.aacproject.service.UserService;
import com.passon.aacproject.service.base.ErrorHandleDisableObserver;
import com.passon.aacproject.service.base.RxService;
import com.passon.aacproject.utils.RxUtils;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午3:21
 * Description: 单例 用户信息 获取 处理 类
 */
public class UserRepository implements IUserRepository {

    private UserRepository() {

    }

    private static class Holder {
        public static final UserRepository sInstance = new UserRepository();
    }

    public static UserRepository getInstance() {
        return Holder.sInstance;
    }

    private String mUserId;

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    @Override
    public void getUserInfo(ErrorHandleDisableObserver<BaseResponse<User>> observer) {
        RxService.createApi(UserService.class)
                .getUserInfo(mUserId)
                .compose(RxUtils.io2Main())
                .subscribe(observer);
    }

}
