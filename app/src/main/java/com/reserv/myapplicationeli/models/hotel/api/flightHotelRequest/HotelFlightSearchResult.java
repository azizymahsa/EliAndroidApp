package com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest;

import com.reserv.myapplicationeli.models.hotel.api.Errors;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult;

import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelFlightSearchResult {
    public final com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult HotelSearchResult;
    public final String ResultUniqID ;
    public final List<Errors> Errors ;

    public HotelFlightSearchResult(com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult hotelSearchResult, String resultUniqID, List<com.reserv.myapplicationeli.models.hotel.api.Errors> errors) {
        HotelSearchResult = hotelSearchResult;
        ResultUniqID = resultUniqID;
        Errors = errors;
    }
}
