package com.reserv.myapplicationeli.models.hotel.api.call;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class Rooms {
    public final Integer AdultCount;
    public final Integer ChildCount;

    public Rooms(Integer adultCount, Integer childCount) {
        AdultCount = adultCount;
        ChildCount = childCount;
    }
}
