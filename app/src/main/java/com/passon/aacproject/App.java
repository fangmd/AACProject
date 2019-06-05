package com.passon.aacproject;

import android.app.Application;
import android.util.Log;

import com.lhjx.loglib.LoggerUtils;

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
        LoggerUtils.init(BuildConfig.DEBUG);
    }
}
