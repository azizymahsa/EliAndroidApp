package com.eligasht.service.generator;

import com.eligasht.service.api.RetroClient;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class ServiceGenerator {
    private Retrofit retrofit;

    @Inject
    public ServiceGenerator(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public RetroClient createService() {
        return retrofit.create(RetroClient.class);
    }


}
