package com.eligasht.reservation.models.eventbus;

/**
 * Created by Reza.nejati on 4/7/2018.
 */

public class CommentModelBus {
    String hotelName;
    String HotelId;

    public CommentModelBus(String hotelName, String hotelId) {
        this.hotelName = hotelName;
        HotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelId() {
        return HotelId;
    }

    public void setHotelId(String hotelId) {
        HotelId = hotelId;
    }
}
