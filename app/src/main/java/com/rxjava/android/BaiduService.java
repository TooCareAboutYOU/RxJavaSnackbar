package com.rxjava.android;

import android.database.Observable;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public interface BaiduService<T> {

//    @GET("/")
//    Flowable<ResponseBody> getResponse();

    @GET("/")
    Flowable<T> getResponse();

    @GET("/")
    Observable<JsonBean> getT();
}
