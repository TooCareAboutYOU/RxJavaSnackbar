package com.model_rxjava.main.rxjava;

/**
 *
 */
public class MyLocalObservable extends LocalSubject {

    public void publishMsg(Object obj){
        notifyLocalObservers(obj);
    }

}
