package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelAvailResult {
    public final HotelSearchResult HotelSearchResult;
    public final String ResultUniqID ;
    public final String Error ;

    public HotelAvailResult(com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelSearchResult hotelSearchResult, String resultUniqID, String error) {
        HotelSearchResult = hotelSearchResult;
        ResultUniqID = resultUniqID;
        Error = error;
    }
}
