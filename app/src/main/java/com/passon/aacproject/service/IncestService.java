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
public interface IncestService {

    @GET(ApiC.Invest.INVEST)
    Observable<BaseResponse<User>> invest(@Query("id") String userId);

}
