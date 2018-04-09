package com.eligasht.service.listener;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public interface OnServiceStatus<T> {

    void onReady(T t);


    void onError(String message);
}
