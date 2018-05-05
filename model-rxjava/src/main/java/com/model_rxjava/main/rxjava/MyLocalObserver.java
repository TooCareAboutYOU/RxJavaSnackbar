package com.model_rxjava.main.rxjava;

/**
 *
 */
public class MyLocalObserver implements LocalObserver {

    private String name;

    public MyLocalObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Object obj) {
        System.out.println(this.name+""+obj);
    }
}
