package com.passon.aacproject.base;

import com.passon.aacproject.entity.ErrorEnvelope;
import com.passon.loglib.LoggerUtils;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Author: Created by fangmingdong on 2018/8/29-下午4:01
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onResume() {
        super.onResume();
        //umeng
    }

    @Override
    protected void onStop() {
        super.onStop();
        //umeng
    }

    protected void onError(ErrorEnvelope errorEnvelope) {
        LoggerUtils.e(errorEnvelope.toString());
    }

}
