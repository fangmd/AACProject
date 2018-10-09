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
public class UserRepositoryRetrofit implements IUserRepository {

    private UserRepositoryRetrofit() {

    }

    private static class Holder {
        public static final IUserRepository sInstance = new UserRepositoryRetrofit();
    }

    public static IUserRepository getInstance() {
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

//
//        RxService.createApi(UserService.class)
//                .getUserInfo2(mUserId)
//                .compose(RxUtils.io2MainSingle())
//                .subscribe(new SingleObserver<BaseResponse<User>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(BaseResponse<User> userBaseResponse) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
    }

}
