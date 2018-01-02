package com.reserv.myapplicationeli.slidingmenu.base;

import android.app.Application;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.font.CustomViewWithTypefaceSupport;
import com.reserv.myapplicationeli.slidingmenu.font.TextField;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/irsans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
                .addCustomStyle(TextField.class, R.attr.textFieldStyle)
                .build()
        );

    }
}
