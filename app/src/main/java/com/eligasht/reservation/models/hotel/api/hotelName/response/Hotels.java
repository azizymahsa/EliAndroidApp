package com.eligasht.reservation.models.hotel.api.hotelName.response;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class Hotels {
    public final String HotelID;
    public final String HotelNameEn;
    public final String HotelNameFa;

    public Hotels(String hotelID, String hotelNameEn, String hotelNameFa) {
        HotelID = hotelID;
        HotelNameEn = hotelNameEn;
        HotelNameFa = hotelNameFa;
    }
}
