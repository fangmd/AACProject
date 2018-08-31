package com.passon.aacproject.service;

import com.passon.aacproject.entity.BaseResponse;
import com.passon.aacproject.entity.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午2:09
 * Description:
 */
public interface UserService {

    @GET(ApiC.User.GET_INFO)
    Observable<BaseResponse<User>> getUserInfo(@Query("id") String userId);

}
