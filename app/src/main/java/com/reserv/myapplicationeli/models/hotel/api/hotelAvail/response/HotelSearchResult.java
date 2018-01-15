package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelSearchResult {
    public final List<Hotels> Hotels;
    public final List<HotelTypes> HotelTypes;
    public final ArrayList<Facilities> Facilities;
    public final int MaxPrice;
    public final int MinPrice;

    public HotelSearchResult(List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels>
                                     hotels, List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelTypes> hotelTypes,
                             ArrayList<Facilities> facilities, int maxPrice, int minPrice) {
        Hotels = hotels;
        HotelTypes = hotelTypes;
        Facilities = facilities;
        MaxPrice = maxPrice;
        MinPrice = minPrice;
    }
}
