package com.reserv.myapplicationeli.models.hotel.api.rooms.response;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class RoomList {
    public final String Board;
    public final String Description;
    public final String Title;
    public final String Price;
    public final String EHotelId;
    public final String OfferId;

    public RoomList(String board, String description, String title, String price, String EHotelId, String offerId) {
        Board = board;
        Description = description;
        Title = title;
        Price = price;
        this.EHotelId = EHotelId;
        OfferId = offerId;
    }
}
