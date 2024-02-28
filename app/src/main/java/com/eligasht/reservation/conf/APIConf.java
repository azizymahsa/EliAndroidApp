package com.eligasht.reservation.conf;

import android.util.Log;

import com.eligasht.reservation.tools.Prefs;
import com.eligasht.service.helper.Const;

/**
 * Authors:
 * Mahsa Azizi
 * Copyright © 2017
 */
public class APIConf extends BaseConf {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public static String CORE_REST_API_URI;

    public APIConf() {
        CORE_REST_API_URI = Prefs.getString("BASEURL", "")+"/";
        Log.d("onClick APIConf ",CORE_REST_API_URI);
    }

    static {

     // CORE_REST_API_URI = "https://mobilews.eligasht.com/LightServices/Rest/";
        if(Prefs.getString("BASEURL", "") !=null || Prefs.getString("BASEURL", "")!="")
            CORE_REST_API_URI = "";
        else
            CORE_REST_API_URI = Prefs.getString("BASEURL", "")+"/";

        Log.d("onClick3: ",CORE_REST_API_URI);
    //  CORE_REST_API_URI = ";
    }
}
