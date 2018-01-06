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


    public Hotels(Availability availability, String name, String city, String location, String mainImage, int starRating) {
        Availability = availability;
        Name = name;
        City = city;
        Location = location;
        MainImage = mainImage;
        StarRating = starRating;
    }
}

