package com.passon.aacproject.repository;

import com.passon.aacproject.entity.BaseResponse;
import com.passon.aacproject.entity.User;
import com.passon.aacproject.service.base.ErrorHandleDisableObserver;
import com.passon.aacproject.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午3:21
 * Description: 单例 用户信息 获取 处理 类
 */
public class UserRepositoryHttpClient implements IUserRepository {

    private UserRepositoryHttpClient() {

    }

    private static class Holder {
        public static final IUserRepository sInstance = new UserRepositoryHttpClient();
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
        Observable.create((ObservableOnSubscribe<BaseResponse<User>>) emitter -> {
            //net work
            BaseResponse<User> value = new BaseResponse<>();
            // ----
            emitter.onNext(value);
            emitter.onComplete();
        }).compose(RxUtils.io2Main())
                .subscribe(observer);
    }

}
