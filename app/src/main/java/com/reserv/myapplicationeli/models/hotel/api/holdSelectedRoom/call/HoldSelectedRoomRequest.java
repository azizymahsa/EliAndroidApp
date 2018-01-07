package com.reserv.myapplicationeli.models.hotel.api.holdSelectedRoom.call;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Request;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class HoldSelectedRoomRequest {
    RoomRequest request;
    public HoldSelectedRoomRequest(RoomRequest request) {
        this.request = request;
    }
}
