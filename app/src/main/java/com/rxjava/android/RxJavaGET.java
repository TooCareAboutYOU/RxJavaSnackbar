package com.rxjava.android;

import android.content.Context;
import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class RxJavaGET{

    private String BASE_URL="";  //接口头
    private Retrofit retrofit;
    private OkHttpClient mOkHttpClient;//okHttpClient 实例
    private Handler okHttpHandler;//全局处理子线程和M主线程通信

    private static final byte[] sInstanceLock = new byte[0];
    private volatile static RxJavaGET sInstance;

    /*初始化*/
    private RxJavaGET(Context context, String BaseUrl) {
        this.BASE_URL=BaseUrl;
        final Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").serializeNulls().create();
        retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // 添加RxJava2的适配器支持
            .build();

        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //初始化Handler
        okHttpHandler = new Handler(context.getMainLooper());
    }

    /*单例模式*/
    public static RxJavaGET getInstance(Context context,String url) {
        if (sInstance == null) {
            synchronized (sInstanceLock) {
                if (sInstance == null) {
                    sInstance = new RxJavaGET(context,url);
                }
            }
        }
        return sInstance;
    }


    public <T> Call Hello(final RxJavaCallBack<T> callBack){
        final Call call=null;
        final T t=null;
        final Class<T> cla=null;
        BaiduService service=retrofit.create(BaiduService.class);
        service.getResponse()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(final T bean) {
                        System.out.println("我的网络数据："+bean.toString());
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                failedCallBack("访问失败",callBack);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String str = response.body().string();
                                T model= JSON.parseObject(str,cla);
                                successCallBack(bean,callBack);
                            }
                        });

                        System.out.println("--------------------优美分割线---------------------------");

                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        System.out.println("出错");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("完成");
                    }
                });
        return call;
    }

    /**
     * 统一同意处理成功信息
     * @param result
     * @param callBack
     * @param <T>
     */
    private <T> void successCallBack(final T result, final RxJavaCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onSuccess(result);
                }
            }
        });
    }

    /**
     * 统一处理失败信息
     * @param errorMsg
     * @param callBack
     * @param <T>
     */
    private <T> void failedCallBack(final String errorMsg, final RxJavaCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFailed(errorMsg);
                }
            }
        });
    }

    /*
    * 定义好请求接口。注意，返回值是 Flowable 类型。
    * 在创建 Retrofit 的时候添加对 RJ 2 的适配器，
    * 这样，请求就可以直接返回 Flowable。
    * 然后就可以进行 RJ 2 的操作了。
    * */
//    private void Hello(String url){
//        final Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").serializeNulls().create();
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // 添加RxJava2的适配器支持
//                .build();
//
//        BaiduService service=retrofit.create(BaiduService.class);
//        service.getResponse()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<JsonBean>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        s.request(Long.MAX_VALUE);
//                    }
//
//                    @Override
//                    public void onNext(JsonBean jsonBean) {
//                        System.out.println("我的网络数据："+jsonBean.toString());
//                        System.out.println("--------------------优美分割线---------------------------");
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        t.printStackTrace();
//                        System.out.println("出错");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("完成");
//                    }
//                });
//    }
}
