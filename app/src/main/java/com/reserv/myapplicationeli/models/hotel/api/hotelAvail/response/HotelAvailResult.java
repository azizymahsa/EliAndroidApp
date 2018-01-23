package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

import com.reserv.myapplicationeli.models.hotel.api.Errors;

import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelAvailResult {
    public final HotelSearchResult HotelSearchResult;
    public final String ResultUniqID ;
    public final List<com.reserv.myapplicationeli.models.hotel.api.Errors> Errors ;

    public HotelAvailResult(com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult hotelSearchResult, String resultUniqID, List<com.reserv.myapplicationeli.models.hotel.api.Errors> errors) {
        HotelSearchResult = hotelSearchResult;
        ResultUniqID = resultUniqID;
        Errors = errors;
    }
}
