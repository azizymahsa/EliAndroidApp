package com.eligasht.reservation.models.eventbus;



import com.eligasht.service.model.newModel.hotel.hotelDetail.response.HotelProprty;

import java.util.List;

/**
 * Created by Reza.nejati on 4/7/2018.
 */

public class HotelProprtiesBus {
    private List<HotelProprty> hotel;

    public HotelProprtiesBus(List<HotelProprty> hotel) {
        this.hotel = hotel;
    }

    public List<HotelProprty> getHotel() {
        return hotel;
    }
}
