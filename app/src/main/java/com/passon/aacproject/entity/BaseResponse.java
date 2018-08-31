package com.passon.aacproject.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午2:30
 * Description:
 */
public class BaseResponse<T> {
    /**
     * code : 200
     * data : {"downloadurl":"https://itunes.apple.com/app/apple-store/id952051350?pt=117132209&ct=marketingWangcaigu&mt=8","version_code":2,"version_name":"1.1","update_content":"账户投资记录优化，修复已知问题","update_type":0}
     * message : asd
     * status : success
     */

    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public T data;
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public String status;


}
