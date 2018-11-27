package com.passon.aacproject.module.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.passon.aacproject.R;
import com.passon.aacproject.base.BaseActivity;
import com.passon.aacproject.entity.ErrorEnvelope;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends BaseActivity {

    private SplashViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        mViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        mViewModel.onError().observe(this, this::onError);

        Disposable subscribe = Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> mViewModel.showMain(SplashActivity.this));
    }

    @Override
    protected void onError(ErrorEnvelope errorEnvelope) {
        super.onError(errorEnvelope);
    }
}
