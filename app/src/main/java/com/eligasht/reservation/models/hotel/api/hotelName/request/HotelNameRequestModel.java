package com.eligasht.reservation.models.hotel.api.hotelName.request;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class HotelNameRequestModel {
    public final String PropertyCode;
    public final String Culture;
    public final Identity identity;

    public HotelNameRequestModel(String propertyCode, String culture, Identity identity) {
        PropertyCode = propertyCode;
        Culture = culture;
        this.identity = identity;
    }
}
