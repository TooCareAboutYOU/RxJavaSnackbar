package com.model_rxjava.main.rxjava;

/**
 *
 */
public class Client {
    public static void main(String[] args) {
        LocalObserver observer1=new MyLocalObserver("大家好，我是Tom，");
        LocalObserver observer2=new MyLocalObserver("大家好，我是Dean，");
        LocalObserver observer3=new MyLocalObserver("大家好，我是Tomas，");
        MyLocalObservable subject=new MyLocalObservable();
        subject.addLocalObserver(observer1);
        subject.addLocalObserver(observer2);
        subject.addLocalObserver(observer3);
        subject.publishMsg("我来自客户端1");
        subject.removeLocalObserver(observer2);
        subject.publishMsg("我来自客户端2");
    }
}
