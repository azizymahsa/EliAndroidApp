package com.eligasht.service.generator;

import com.eligasht.service.api.RetroClient;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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