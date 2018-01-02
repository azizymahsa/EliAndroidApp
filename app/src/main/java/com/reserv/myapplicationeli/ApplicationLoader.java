package com.reserv.myapplicationeli;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by elham.bonyani on 1/2/2018.
 */

public class ApplicationLoader extends Application {


    private static ApplicationLoader mInstance;
    public static volatile Context applicationContext;
    public static volatile Handler applicationHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        applicationContext = getApplicationContext();
        applicationHandler = new Handler(applicationContext.getMainLooper());
    }
}
