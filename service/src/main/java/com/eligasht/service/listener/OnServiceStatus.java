package com.eligasht.service.listener;

import android.support.annotation.UiThread;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public interface OnServiceStatus<T> {
    @UiThread
    void onReady(T t);

    @UiThread
    void onError(String  message);
}
