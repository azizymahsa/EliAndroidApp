package com.eligasht.reservation.models.hotel.api.hotelAvail.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelSearchResult {



    public final List<com.eligasht.reservation.models.hotel.api.hotelAvail.response.Hotels> Hotels;
    public final List<com.eligasht.reservation.models.hotel.api.hotelAvail.response.HotelTypes> HotelTypes;
    public final ArrayList<Facilities> Facilities;
    public final ArrayList<Locations> Locations;
    public final com.eligasht.reservation.models.hotel.api.flightHotelRequest.Flights Flights;
    public final int MaxPrice;
    public final int MinPrice;

    public HotelSearchResult(List<com.eligasht.reservation.models.hotel.api.hotelAvail.response.Hotels> hotels, List<com.eligasht.reservation.models.hotel.api.hotelAvail.response.HotelTypes> hotelTypes, ArrayList<com.eligasht.reservation.models.hotel.api.hotelAvail.response.Facilities> facilities, ArrayList<com.eligasht.reservation.models.hotel.api.hotelAvail.response.Locations> locations, com.eligasht.reservation.models.hotel.api.flightHotelRequest.Flights flights, int maxPrice, int minPrice) {
        Hotels = hotels;
        HotelTypes = hotelTypes;
        Facilities = facilities;
        Locations = locations;
        Flights = flights;
        MaxPrice = maxPrice;
        MinPrice = minPrice;
    }
}


