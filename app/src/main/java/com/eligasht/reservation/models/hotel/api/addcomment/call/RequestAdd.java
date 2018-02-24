package com.eligasht.reservation.models.hotel.api.addcomment.call;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class RequestAdd {
    public final Identity identity;
    public final String Culture;
    public final HotelReviewModel HotelReviewModel;


    public RequestAdd(Identity identity, String culture, com.eligasht.reservation.models.hotel.api.addcomment.call.HotelReviewModel hotelReviewModel) {
        this.identity = identity;
        Culture = culture;
        HotelReviewModel = hotelReviewModel;
    }
}
