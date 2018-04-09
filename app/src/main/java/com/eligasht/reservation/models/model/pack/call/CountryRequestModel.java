package com.eligasht.reservation.models.model.pack.call;


import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by elham.bonyani on 1/5/2018.
 */

public class CountryRequestModel {
  public final CountryListReq request;

    public CountryRequestModel(CountryListReq request) {
        Log.e("CountryRequestModel:", new Gson().toJson(request) );
        this.request = request;
    }
}
