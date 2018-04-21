package com.eligasht.reservation.views.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;


import com.eligasht.R;

import java.util.Locale;

import okhttp3.OkHttpClient;

/**
 * Created by Ahmad.nemati on 3/5/2018.
 */

public class SingletonContext {
    private static final SingletonContext ourInstance = new SingletonContext();
    private Context context;
    private Typeface typeface;
    private OkHttpClient okHttpClient;

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

    public Typeface getTypeface() {
        if (typeface != null)
            return typeface;
        typeface = Typeface.createFromAsset(
                context.getAssets(),
                context.getResources().getString(R.string.iran_sans_normal_ttf));
        return typeface;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
}
