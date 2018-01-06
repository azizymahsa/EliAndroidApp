package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class Availability {
    public final List<RoomLists> RoomLists;

    public Availability(List<RoomLists> roomLists) {
        RoomLists = roomLists;
    }
}
