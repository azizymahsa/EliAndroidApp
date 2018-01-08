package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class Hotels {
    public final Availability Availability;
    public final String Name;
    public final String City;
    public final String Location;
    public final String MainImage;
    public final int StarRating;
    public final boolean BestSell;
    public final String TypeText;


    public Hotels(com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Availability
                          availability, String name, String city, String location, String mainImage,
                  int starRating, boolean bestSell, String typeText) {
        Availability = availability;
        Name = name;
        City = city;
        Location = location;
        MainImage = mainImage;
        StarRating = starRating;
        BestSell = bestSell;
        TypeText = typeText;
    }
}

