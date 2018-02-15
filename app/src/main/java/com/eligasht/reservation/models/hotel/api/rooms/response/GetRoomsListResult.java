package com.eligasht.reservation.models.hotel.api.rooms.response;

import com.eligasht.reservation.models.model.Errors;

import java.util.List;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class GetRoomsListResult {
    public final String SearchKey;
    public final List<RoomList> roomList;
    public final List<Errors> errors;

    public GetRoomsListResult(String searchKey, List<RoomList> roomList, List<Errors> errors) {
        SearchKey = searchKey;
        this.roomList = roomList;
        this.errors = errors;
    }
}
