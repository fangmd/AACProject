package com.passon.aacproject.entity;

import com.passon.aacproject.C;

/**
 * Author: Created by fangmingdong on 2018/8/30-上午10:53
 * Description:
 */
public class ErrorEnvelope {

    public final int code;
    public final String message;
    private final Throwable throwable;

    public ErrorEnvelope(String message) {
        this(C.ErrorCode.UNKNOWN, message);
    }

    public ErrorEnvelope(int code, String message) {
        this(code, message, null);
    }

    public ErrorEnvelope(int code, String message, Throwable throwable) {
        if (message == null) {
            message = "";
        }
        if (throwable == null) {
            throwable = new Throwable("unknown");
        }
        this.code = code;
        this.message = message;
        this.throwable = throwable;
    }

    @Override
    public String toString() {
        return "code:" + code + ", message:" + message + ", throwable:" + throwable.getMessage();
    }
}
