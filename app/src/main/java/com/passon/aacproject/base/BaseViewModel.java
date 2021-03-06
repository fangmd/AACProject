package com.passon.aacproject.base;

import com.passon.aacproject.entity.ErrorEnvelope;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author: Created by fangmingdong on 2018/8/30-上午9:48
 * Description:
 */
public class BaseViewModel extends ViewModel {

    protected MutableLiveData<ErrorEnvelope> mError = new MutableLiveData<>();

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public LiveData<ErrorEnvelope> onError() {
        return mError;
    }

    @Override
    protected void onCleared() {
        cancel();
    }

    protected void addDisposable(Disposable d) {
        mCompositeDisposable.add(d);
    }

    protected void cancel() {
        mCompositeDisposable.clear();
    }

    protected void postError(int code, String msg) {
        mError.postValue(new ErrorEnvelope(code, msg));
    }

}
