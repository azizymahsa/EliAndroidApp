package com.eligasht.reservation.models.hotel.api.hotelAvail.response;

import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelAvailResult {
    public final HotelSearchResult HotelSearchResult;
    public final String ResultUniqID ;
    public final List<com.eligasht.reservation.models.hotel.api.Errors> Errors ;

    public HotelAvailResult(com.eligasht.reservation.models.hotel.api.hotelAvail.response.HotelSearchResult hotelSearchResult, String resultUniqID, List<com.eligasht.reservation.models.hotel.api.Errors> errors) {
        HotelSearchResult = hotelSearchResult;
        ResultUniqID = resultUniqID;
        Errors = errors;
    }
}
