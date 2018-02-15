package com.eligasht.reservation.models.hotel.api.flightHotelRequest;

import com.eligasht.reservation.models.hotel.api.Errors;

import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelFlightSearchResult {
    public final com.eligasht.reservation.models.hotel.api.hotelAvail.response.HotelSearchResult HotelSearchResult;
    public final String ResultUniqID ;
    public final List<Errors> Errors ;

    public HotelFlightSearchResult(com.eligasht.reservation.models.hotel.api.hotelAvail.response.HotelSearchResult hotelSearchResult, String resultUniqID, List<com.eligasht.reservation.models.hotel.api.Errors> errors) {
        HotelSearchResult = hotelSearchResult;
        ResultUniqID = resultUniqID;
        Errors = errors;
    }
}
