package com.reserv.myapplicationeli.models.hotel.api.holdSelectedRoom.response;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class HoldSelectedRoomResult {
    public final String Board;
    public final String EHotelId;
    public final String SourcePrice;
    public final String OfferId;

    public HoldSelectedRoomResult(String board, String EHotelId, String sourcePrice, String offerId) {
        Board = board;
        this.EHotelId = EHotelId;
        SourcePrice = sourcePrice;
        OfferId = offerId;
    }
}
