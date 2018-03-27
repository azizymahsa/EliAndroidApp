package com.eligasht.service.api;

import com.eligasht.service.observable.CustomObservable;
import com.eligasht.service.helper.Const;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public interface RetroClient {
    @POST(Const.HotelAvail)
    Observable<HotelAvailRes> hotelAvail(
            @Body HotelAvailReq hotelAvailReq
    );
}
