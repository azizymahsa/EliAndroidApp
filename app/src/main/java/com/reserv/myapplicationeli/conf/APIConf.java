package com.reserv.myapplicationeli.conf;

/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 * Copyright © 2017
 */
public class APIConf extends BaseConf {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public static final String CORE_REST_API_URI;

    static {

      CORE_REST_API_URI = "http://mobilews.eligasht.com/LightServices/Rest/";
    }
}
