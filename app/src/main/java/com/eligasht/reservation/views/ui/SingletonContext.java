package com.eligasht.reservation.views.ui;

import android.content.Context;

/**
 * Created by Ahmad.nemati on 3/5/2018.
 */

public class SingletonContext {
    private static final SingletonContext ourInstance = new SingletonContext();
    private Context context;

    private SingletonContext() {

    }

    public static SingletonContext getInstance() {
        return ourInstance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
