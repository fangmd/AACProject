package com.passon.aacproject.module.account;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.lhjx.loglib.LoggerUtils;
import com.passon.aacproject.base.BaseViewModel;
import com.passon.aacproject.entity.BaseResponse;
import com.passon.aacproject.entity.User;
import com.passon.aacproject.repository.UserRepository;
import com.passon.aacproject.service.base.ErrorHandleDisableObserver;
import com.passon.aacproject.utils.RxUtils;

import java.util.concurrent.TimeUnit;

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
        ErrorHandleDisableObserver<BaseResponse<User>> observer = new ErrorHandleDisableObserver<BaseResponse<User>>() {

            @Override
            public void onSuccess(BaseResponse<User> userBaseResponse) {
                mUserMutableLiveData.postValue(userBaseResponse.data);
            }

            @Override
            public void onFailure(int code, String msg) {
                postError(code, msg);
            }
        };
        UserRepository.getInstance().getUserInfo(observer);
        addDisposable(observer);
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
