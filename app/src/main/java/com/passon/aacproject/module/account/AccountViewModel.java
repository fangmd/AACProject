package com.passon.aacproject.module.account;

import com.passon.aacproject.base.BaseViewModel;
import com.passon.aacproject.entity.User;
import com.passon.aacproject.repository.UserRepository;
import com.passon.aacproject.service.BaseNetSingleDisableObserver;
import com.passon.commonutils.RxUtils;
import com.passon.loglib.LoggerUtils;
import com.passon.netlib.BaseResp;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午2:28
 * Description:
 */
public class AccountViewModel extends BaseViewModel {

    private MutableLiveData<User> mUserMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Long> mCnt = new MutableLiveData<>();

    public AccountViewModel() {

    }

    public void init() {
        getUserInfo();
    }

    public LiveData<User> user() {
        return mUserMutableLiveData;
    }

    public LiveData<Long> cnt() {
        return mCnt;
    }

    private void getUserInfo() {
//        BaseNetDisableObserver<BaseResp<User>> observer = new BaseNetDisableObserver<BaseResp<User>>() {
//
//            @Override
//            public void onNext(BaseResp<User> userBaseResponse) {
//                mUserMutableLiveData.postValue(userBaseResponse.data);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                postError(C.ErrorCode.UNKNOWN, e.getMessage());
//            }
//        };
        UserRepository userRepository = new UserRepository();
//        userRepository.getUserInfo(observer);
//        addDisposable(observer);


        // single
        BaseNetSingleDisableObserver<BaseResp<User>> singleObserver = new BaseNetSingleDisableObserver<BaseResp<User>>() {
            @Override
            public void onSuccess(BaseResp<User> userBaseResp) {

            }

            @Override
            public void onError(Throwable e) {

            }
        };
        userRepository.getUserInfo2().subscribe(singleObserver);
        addDisposable(singleObserver);
    }

    public void startInterval() {
        Disposable subscribe = Observable.interval(500, TimeUnit.MILLISECONDS)
                .compose(RxUtils.io2Main())
                .subscribe(aLong -> {
                    LoggerUtils.d(aLong + "");
                    mCnt.postValue(aLong);
                });
        addDisposable(subscribe);
    }

}
