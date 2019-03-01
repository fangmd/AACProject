package com.passon.aacproject.service.base;


import com.lhjx.loglib.LoggerUtils;
import com.passon.aacproject.service.DownloadService;
import com.passon.aacproject.utils.FileUtils;
import com.passon.aacproject.utils.RxUtils;

import java.io.File;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Author: Created by fangmingdong on 2018/11/1-9:14 AM
 * Description:
 */
public class RxDownload {

    /**
     * 下载功能
     * 1. 同名文件，覆盖
     *
     * @param file 文件下载地址，包含文件名
     * @param url  下载 url
     * @return Single<String>
     */
    public static Single<String> donwlaod(File file, String url) {
        DownloadService downloadService = RxService.getDownlaodRetrofit(true).create(DownloadService.class);
        return downloadService.downloadFile(url)
                .map((Function<ResponseBody, String>) responseBody -> {
                    LoggerUtils.d("download start write file");
                    FileUtils.writeFile(file, responseBody.byteStream());
                    return "success";
                })
                .compose(RxUtils.io2MainSingle());
    }
}
