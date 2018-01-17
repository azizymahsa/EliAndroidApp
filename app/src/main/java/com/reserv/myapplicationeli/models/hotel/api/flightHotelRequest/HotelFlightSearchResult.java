package com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelFlightSearchResult {
    public final com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult HotelSearchResult;
    public final String ResultUniqID ;

    public HotelFlightSearchResult(com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult hotelSearchResult, String resultUniqID) {
        HotelSearchResult = hotelSearchResult;
        ResultUniqID = resultUniqID;
    }
}
