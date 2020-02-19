package com.passon.aacproject;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.passon.commonutils.CommonUtils;
import com.passon.loglib.LoggerUtils;
import com.passon.netlib.NetManager;

import androidx.multidex.MultiDex;

/**
 * Author: Created by fangmingdong on 2018/8/29-下午4:05
 * Description:
 */
public class App extends Application {

    public static Application sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        sInstance = this;
        Log.d("tag", "onCreate: ");

        initLib();

        new View().setOnTouchListener();

        String sourceDir = getApplicationInfo().sourceDir;
        LoggerUtils.d(sourceDir);
    }

    private void initLib() {
        LoggerUtils.init(BuildConfig.DEBUG);
        NetManager.init(sInstance, BuildConfig.API_URL, BuildConfig.DEBUG);
        CommonUtils.init(this);
    }
}
