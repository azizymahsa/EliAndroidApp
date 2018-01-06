package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelAvailResult {
    public final HotelSearchResult HotelSearchResult;
    public final String ResultUniqID ;

    public HotelAvailResult(HotelSearchResult hotelSearchResult, String resultUniqID) {
        HotelSearchResult = hotelSearchResult;
        ResultUniqID = resultUniqID;
    }
}
