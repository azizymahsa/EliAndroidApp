package com.reserv.myapplicationeli.models.hotel.api.rooms.response;

import java.util.List;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class GetRoomsListResult {
    public final String SearchKey;
    public final List<RoomList> roomList;

    public GetRoomsListResult(String searchKey, List<RoomList> roomList) {
        SearchKey = searchKey;
        this.roomList = roomList;
    }
}
