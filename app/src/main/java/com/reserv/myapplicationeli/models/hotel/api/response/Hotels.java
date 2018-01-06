package com.reserv.myapplicationeli.models.hotel.api.response;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class Hotels {
    public final Availability Availability;
    public final String Name;
    public final String City;
    public final String Location;
    public final String MainImage;


    public Hotels(com.reserv.myapplicationeli.models.hotel.api.response.Availability availability,
                  String name, String city, String location, String mainImage) {
        Availability = availability;
        Name = name;
        City = city;
        Location = location;
        MainImage = mainImage;
    }
}

