package com.passon.aacproject.service;

import io.reactivex.observers.DisposableObserver;

/**
 * Author: Created by fangmingdong on 2019-08-07-17:50
 * Description:
 */
public abstract class BaseNetDisableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onComplete() {

    }
}