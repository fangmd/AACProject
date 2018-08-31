package com.passon.aacproject.repository;

import com.passon.aacproject.entity.BaseResponse;
import com.passon.aacproject.entity.User;
import com.passon.aacproject.service.base.ErrorHandleDisableObserver;

/**
 * Author: Created by fangmingdong on 2018/8/30-下午3:21
 * Description:
 */
public interface IUserRepository {

    void getUserInfo(ErrorHandleDisableObserver<BaseResponse<User>> observer);

}
