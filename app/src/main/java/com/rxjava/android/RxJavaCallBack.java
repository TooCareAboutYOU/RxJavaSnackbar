package com.rxjava.android;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public interface RxJavaCallBack<T> {
    void onSuccess(T t);
    void onFailed(String eMsg);
}
