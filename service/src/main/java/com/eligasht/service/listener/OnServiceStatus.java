package com.eligasht.service.listener;

import android.support.annotation.UiThread;

import com.eligasht.service.exception.ServiceException;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */
@UiThread
public interface OnServiceStatus<T> {
    void onReady(T t);

    void onError(Throwable error);
}
