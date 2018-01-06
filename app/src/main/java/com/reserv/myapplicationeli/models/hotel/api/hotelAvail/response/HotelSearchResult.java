package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelSearchResult {
    public final List<Hotels> Hotels;
    public final int MaxPrice;
    public final int MinPrice;

    public HotelSearchResult(List<Hotels> hotels, int maxPrice, int minPrice) {
        Hotels = hotels;
        MaxPrice = maxPrice;
        MinPrice = minPrice;
    }
}
