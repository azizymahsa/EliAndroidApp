package com.eligasht.reservation.models.eventbus;

import com.eligasht.reservation.models.hotel.api.detail.call.HotelProprties;

import java.util.List;

/**
 * Created by Reza.nejati on 4/7/2018.
 */

public class HotelProprtiesBus {
    private List<HotelProprties> hotel;

    public HotelProprtiesBus(List<HotelProprties> hotel) {
        this.hotel = hotel;
    }

    public List<HotelProprties> getHotel() {
        return hotel;
    }
}
