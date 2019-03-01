package com.passon.aacproject.service;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Author: Created by fangmingdong on 2018/11/1-9:15 AM
 * Description:
 */
public interface DownloadService {

    @GET
    Single<ResponseBody> downloadFile(@Url String fileUrl);

}
