package com.eligasht.reservation.models.eventbus;

import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsModel;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 4/7/2018.
 */

public class RoomsModelBus {
    private ArrayList<RoomsModel> roomsModels;

    public RoomsModelBus(ArrayList<RoomsModel> roomsModels) {
        this.roomsModels = roomsModels;
    }

    public ArrayList<RoomsModel> getRoomsModels() {
        return roomsModels;
    }
}
