package com.eligasht.reservation.models.hotel.api.detail;

import java.util.List;

/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class HotelDetail {
    public final String Address;
    public final String HotelName;
    public final String CountryName;
    public final String CityName;
    public final List<ImageHotel> HotelImages;
    public final String Latitude;
    public final String Longitude;
    public final int StarRating;
    public final List<com.eligasht.reservation.models.hotel.api.detail.call.HotelProprties> HotelProprties;

    public HotelDetail(String address, String hotelName, String countryName, String cityName, List<ImageHotel> hotelImages, String latitude, String longitude, int starRating, List<com.eligasht.reservation.models.hotel.api.detail.call.HotelProprties> hotelProprties) {
        Address = address;
        HotelName = hotelName;
        CountryName = countryName;
        CityName = cityName;
        HotelImages = hotelImages;
        Latitude = latitude;
        Longitude = longitude;
        StarRating = starRating;
        HotelProprties = hotelProprties;
    }
}
