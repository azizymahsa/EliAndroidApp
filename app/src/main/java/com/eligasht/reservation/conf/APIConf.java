package com.eligasht.reservation.conf;

import com.eligasht.reservation.tools.Prefs;

/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 * Copyright © 2017
 */
public class APIConf extends BaseConf {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public static final String CORE_REST_API_URI;

    static {

     // CORE_REST_API_URI = "https://mobilews.eligasht.com/LightServices/Rest/";
        if(Prefs.getString("BASEURL", "") !=null || Prefs.getString("BASEURL", "")!="")
            CORE_REST_API_URI = "http://192.168.1.82:120/";
        else
            CORE_REST_API_URI = Prefs.getString("BASEURL", "")+"/";

    //  CORE_REST_API_URI = "http://192.168.103.74/api/";
    }
}
