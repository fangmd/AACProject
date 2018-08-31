package com.passon.aacproject.module.splash;

import com.passon.aacproject.base.BaseViewModel;
import com.passon.aacproject.module.MainActivity;

/**
 * Author: Created by fangmingdong on 2018/8/30-上午9:48
 * Description:
 */
public class SplashViewModel extends BaseViewModel {


    public void showMain(SplashActivity splashActivity) {
        MainActivity.start(splashActivity);
    }
}
