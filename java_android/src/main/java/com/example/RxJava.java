package com.example;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class RxJava {

    public static void main(String[] args){
        RxJava main=new RxJava();
        main.FlatMapTest();
        main.FilterTest();
        main.TakeTest();
        main.DoNextTest();
        main.DealErrorTest();
        main.BlockingGetTest();
    }

    /*
    * 方式一
    * */
    private void FlowableTest(){
        System.out.println("--------------华丽分割线--------------------");

        //发射器
        Flowable<String> flowable=Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("Hello RxJava 2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);


        /*
        * 在onSubscribe中，我们需要调用request去请求资源，参数就是要请求的数量，
        * 一般如果不限制请求数量，可以写成Long.MAX_VALUE。
        * 如果你不调用request，Subscriber的onNext和onComplete方法将不会被调用。
        * */

        //接收器
        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Subscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {  //onNext方法里面传入的参数就是Flowable中发射出来的。
                System.out.println("s==="+s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };


        //让”发射器”和”接收器”工作起来，需要把他们组装在一起。
        flowable.subscribe(subscriber);

    }

    /*
    * 方式二 简洁版
    * */
    private void EasyFlowableTest(){
        System.out.println("--------------华丽分割线--------------------");

        //发射器
        Flowable<String> flowable=Flowable.just("简洁版：Hello RxJava2");

        /* 接收器
        * 对于 Subscriber 来说，我们目前仅仅关心onNext方法
        * */
        Consumer<String> consumer=new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("简洁版：s==\t"+s);
            }
        };

        flowable.subscribe(consumer);

        System.out.println("--------------华丽分割线--------------------");
        /*
        * 单独定义
        * */
        Flowable.just("简洁版2：Hello RxJava2").subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("单独定义 \n 简洁版2：s==\t"+s);
            }
        });
    }

    /*
    * 变换
    * 不推荐使用
    * */
    private void TransformTest(){
        System.out.println("--------------华丽分割线--------------------");
        //方式一
//        Flowable.just("hello Rxjava2 - zhang")
//                .subscribe(s -> System.out.println(s));

        //方式二
//        Flowable.just("hello Rxjava2")
//                .subscribe(s -> System.out.println(s+"- zhang"));
    }

    /*
    * map 操作符
    * map 是把传递过来的结果末尾加上了签名，然后在传递给了订阅者
    * */
    private void OperatorTest(){
        System.out.println("--------------华丽分割线--------------------");
        Flowable.just("map")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return s + "- zhang";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println("操作符：s==\t"+s);
                    }
                });
    }

    /*
    * map进阶 操作符
    * map 返回任意类型的 Flowable，及可以使用 map 操作符发射一个新的数据类型的 Flowable 对象
    *       订阅者想要得到字符串的hashcode
    * */
    private void MapOperatorTest(){
        System.out.println("--------------华丽分割线--------------------");
        Flowable.just("map进阶")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(@NonNull String s) throws Exception {
                        return s.hashCode(); // 订阅者想要得到字符串的hashcode
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return integer.toString();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println("map操作符：s==\t"+s);
                    }
                });

    }

    /*
    * flatMap
    * Flowable.flatMap 可以把一个 Flowable 转换成另一个 Flowable
    * 返回的是一个 Flowable 对象。把从List发射出来的一个一个的元素发射出去。
    * */
    private void FlatMapTest(){
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(5);

        Flowable.just(list)
                .flatMap(new Function<List<Integer>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(List<Integer> integers) throws Exception {
                        return Flowable.fromIterable(integers);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("FlatMap："+integer);
                    }
                });
    }

    /*
    * filter
    * 是用于过滤数据的，返回false表示拦截此数据。
    * 如果我们想要订阅者只能收到大于5的数据,代码如下：
    * */
    private void FilterTest(){
        System.out.println("---------------------华丽分割线---------------------------");

        Flowable.fromArray(1, 20, 5, 0, -1, 8)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer.intValue() > 5;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.print(integer+"\t\t");
                    }
                });
        System.out.println("\n");
    }


    /*
    * take
    * take 用于指定订阅者最多收到多少数据
    * 如果我们只想要2个数据，代码如下：
    * */
    private void TakeTest(){
        System.out.println("---------------------华丽分割线---------------------------");

        Flowable.fromArray(2,5,7,1,9,4,0,3)
                .take(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.print(integer+"\t\t");
                    }
                });
        System.out.println("\n");
    }

    /*
    * doNext
    * doOnNext 允许我们在每次输出一个元素之前做一些额外的事情
    * 如果我们想在订阅者接收到数据前干点事情，比如记录日志:
    * */
    private void DoNextTest(){
        System.out.println("---------------------华丽分割线---------------------------");

        Flowable.just(1, 2, 3)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.printf("哈哈"+integer+"\t");
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.print(integer+"\t\t");
                    }
                });
    }

    /*
    * 错误处理
    * 下面的代码中，发射数据时，做了一个(1 / 0)的运算，但这明显是会抛出除零异常的。所以，上述代码最后会打印 onError。
    * 而如果改成(1 / 1)，则打印的是 exception:1 和 on complete。
    * 这样的设计有以下几个优点:
    *   1、只要发生错误，onError()一定会被调用。
    *   2、这极大的简化了错误处理。只需要在一个地方处理错误即可以。
    *   3、操作符不需要处理异常。
    *   4、将异常处理交给订阅者来做，一旦有调用链中有一个抛出了异常，就会直接执行onError()方法，停止数据传送。
    *   5、你能够知道什么时候订阅者已经接收了全部的数据。
    * */
    private void DealErrorTest(){
        System.out.println("---------------------华丽分割线---------------------------");
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("exception:" + (1 / 1));
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("on complete");
                    }
                });


        System.out.println("\n");
    }

    /*
    * 调度器
    * 下面代码中，Flowable总共发射了两个数据，但中间延时了3秒，如果在主线程中延时，那将会导致UI卡顿，
    * 所以在订阅之前，我们使用 subscribeOn(Schedulers.io()) 指定了发送数据是在io线程(某个子线程)，
    * 然后调用 observeOn(AndroidSchedulers.mainThread()) 指定订阅者在主线程执行。
    * 对了，要使用 AndroidSchedulers 还需要引入 RxAndroid:
    * compile 'io.reactivex.rxjava2:rxandroid:2.0.0'
    * */
    //只能在安卓项目中调用，java不支持
    private void Test(){
//        System.out.println("---------------------华丽分割线---------------------------");
//        Flowable.create(new FlowableOnSubscribe<String>() {
//            @Override
//            public void subscribe(FlowableEmitter<String> e) throws Exception {
//                e.onNext("将会在3秒后显示");
//                SystemClock.sleep(3000);
//                e.onNext("+++++");
//                e.onComplete();
//            }
//        },BackpressureStrategy.BUFFER)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Consumer<String>() {
//            @Override
//            public void accept(@NonNull String s) throws Exception {
//
//            }
//        });
//        System.out.println("\n");
    }



    /**
    * 从RxJava 2中获取数据
    * 可以通过 fromX 的方法把数组、列表等数据发射出去。
    * 那么有没有办法直接把发射的数据获取出来而不是通过订阅者来输出呢？
    * 这里发射了1到100总计100个数据，我们可以通过 blockingX 方法来拿到这些数据。
    * */
    private void BlockingGetTest(){
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
