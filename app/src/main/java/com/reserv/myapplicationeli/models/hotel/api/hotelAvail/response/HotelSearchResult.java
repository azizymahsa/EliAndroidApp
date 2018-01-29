package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

import com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest.Flights;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelSearchResult {



    public final List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels> Hotels;
    public final List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelTypes> HotelTypes;
    public final ArrayList<Facilities> Facilities;
    public final ArrayList<Locations> Locations;
    public final Flights Flights;
    public final int MaxPrice;
    public final int MinPrice;

    public HotelSearchResult(List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels> hotels, List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelTypes> hotelTypes, ArrayList<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Facilities> facilities, ArrayList<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Locations> locations, com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest.Flights flights, int maxPrice, int minPrice) {
        Hotels = hotels;
        HotelTypes = hotelTypes;
        Facilities = facilities;
        Locations = locations;
        Flights = flights;
        MaxPrice = maxPrice;
        MinPrice = minPrice;
    }
}


