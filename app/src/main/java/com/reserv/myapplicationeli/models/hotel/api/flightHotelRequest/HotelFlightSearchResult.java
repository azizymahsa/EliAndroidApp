package com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelFlightSearchResult {
    public final com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult HotelSearchResult;
    public final String ResultUniqID ;
    public final String Error ;

    public HotelFlightSearchResult(com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult hotelSearchResult, String resultUniqID, String error) {
        HotelSearchResult = hotelSearchResult;
        ResultUniqID = resultUniqID;
        Error = error;
    }
}
