package com.eligasht.reservation.models.hotel.api.rooms.response;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class GetRoomsListResponse {
  public final com.eligasht.reservation.models.hotel.api.rooms.response.GetRoomsListResult GetRoomsListResult;

    public GetRoomsListResponse(com.eligasht.reservation.models.hotel.api.rooms.response.GetRoomsListResult getRoomsListResult) {
        GetRoomsListResult = getRoomsListResult;
    }
}
