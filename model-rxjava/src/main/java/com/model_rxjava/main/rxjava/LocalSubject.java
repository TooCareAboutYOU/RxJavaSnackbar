package com.model_rxjava.main.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 */
public abstract class LocalSubject {


    private List<LocalObserver> mObserverList=new ArrayList<>();

    public void addLocalObserver(LocalObserver observer){
        if (observer == null) {
            throw new NullPointerException();
        }

        synchronized (observer){
            if (!mObserverList.contains(observer)) {
                mObserverList.add(observer);
            }
        }

    }

    public void removeLocalObserver(LocalObserver observer){
        if (observer == null) {
            throw new NullPointerException();
        }
        synchronized (observer){
            if (mObserverList.contains(observer)) {
                mObserverList.remove(observer);
            }
        }
    }

    public void notifyLocalObservers(Object arg){
        synchronized (mObserverList){
            for (LocalObserver localObserver : mObserverList) {
                localObserver.update(arg);
            }
        }
    }



}
