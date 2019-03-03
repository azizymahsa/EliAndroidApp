package com.eligasht.reservation.conf;

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
      CORE_REST_API_URI = "http://192.168.1.82:120/";
    //  CORE_REST_API_URI = "http://192.168.103.74/api/";
    }
}
