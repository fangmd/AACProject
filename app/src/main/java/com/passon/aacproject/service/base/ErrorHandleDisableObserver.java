package com.passon.aacproject.service.base;

import android.net.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.passon.aacproject.App;
import com.passon.aacproject.C;
import com.passon.aacproject.entity.BaseResponse;
import com.passon.aacproject.utils.ToastUtils;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 网络请求，页面回调
 */
public abstract class ErrorHandleDisableObserver<T extends BaseResponse> extends DisposableObserver<T> {

    public static final int SUCCESS_CODE = 0;

    public abstract void onSuccess(T t);

    public abstract void onFailure(int code, String msg);

    private void dealError(Throwable e) {
        int code = -1;
        String msg = "未知错误";
        if (e instanceof UnknownHostException) {
            msg = C.ErrorMsg.ERROR_NETWORK;
        } else if (e instanceof SocketTimeoutException) {
            msg = C.ErrorMsg.ERROR_NETWORK;
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            BaseResponse errorResponse = parseHttpException(httpException);
            code = errorResponse.code;
            msg = "网络异常";
        } else if (e instanceof JsonParseException || e instanceof
                ParseException || e instanceof
                JSONException) {
            msg = "数据解析错误";
        }
        onFailure(code, msg);
        ToastUtils.show(App.sInstance, msg);
    }


    /**
     * 解析httpException
     *
     * @param httpException 异常
     * @return ResponseData
     */
    private BaseResponse parseHttpException(HttpException httpException) {
        ResponseBody responseBody = httpException.response().errorBody();
        MediaType type = responseBody.contentType();
        BaseResponse errorResponse = null;
        // 如果是application/json类型数据,则解析返回内容
        if (type.type().equals("application") && type.subtype().equals("json")) {
            try {
                errorResponse = new Gson().fromJson(
                        responseBody.string(), BaseResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return errorResponse;
    }


    @Override
    public void onNext(T t) {
        try {
            if (t.code == SUCCESS_CODE) {
                onSuccess(t);
            } else {
                //session失效，强制退出，重新登录
                if (C.ErrorMsg.SESSION_INVALID.equals(t.message)) {
//                    LoginOutHelper.loginOut();
                } else {
                    onFailure(t.code, t.message);
                }
            }
        } catch (Exception e) {
            onFailure(0, "数据处理异常");
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable t) {
        try {
            dealError(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }
}
