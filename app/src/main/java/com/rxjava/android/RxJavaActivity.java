package com.rxjava.android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.javaandroid.main.R;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    //RxJavaGET<JsonBean> rxJavaGET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        //Test2();

//        rxJavaGET=new RxJavaGET<JsonBean>("https://api.github.com/");
//        rxJavaGET.setRxInterface(new RxJavaGET.RxJavaInterface<JsonBean>() {
//            @Override
//            public void RsponseBean(JsonBean jsonBean) {
//                System.out.println("最后的结果："+jsonBean.toString());
//            }
//        });

        RxJavaGET.getInstance(this,"https://api.github.com/").Hello(new RxJavaCallBack<JsonBean>() {
            @Override
            public void onSuccess(JsonBean jsonBean) {
                System.out.println("数据获取成功："+jsonBean.toString());
            }

            @Override
            public void onFailed(String eMsg) {
                System.out.println("数据获取失败："+eMsg);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
        * 调度器
        * 下面代码中，Flowable总共发射了两个数据，但中间延时了3秒，如果在主线程中延时，那将会导致UI卡顿，
        * 所以在订阅之前使用 subscribeOn(Schedulers.io()) 指定了发送数据是在io线程(某个子线程)，
        * 然后调用 observeOn(AndroidSchedulers.mainThread()) 指定订阅者在主线程执行。
        * 要使用 AndroidSchedulers 还需要引入 RxAndroid:
        * compile 'io.reactivex.rxjava2:rxandroid:2.0.0'
        * */
    @SuppressLint("CheckResult")
    private void Test(){
        System.out.println("---------------------华丽分割线---------------------------");
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("将会在3秒后显示");
                SystemClock.sleep(3000);
                e.onNext("+++++");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
        System.out.println("\n");
    }



    /*
    * 可以通过 fromX 的方法把数组、列表等数据发射出去。
    * 那么有没有办法直接把发射的数据获取出来而不是通过订阅者来输出呢？
    * 这里发射了1到100总计100个数据，我们可以通过 blockingX 方法来拿到这些数据。
    * */
    private void Test2(){
        List<String> ids=Flowable.range(1,100)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return "id:"+integer;
                    }
                })
                .toList()
                .blockingGet();
        System.out.println(ids);
    }

}
