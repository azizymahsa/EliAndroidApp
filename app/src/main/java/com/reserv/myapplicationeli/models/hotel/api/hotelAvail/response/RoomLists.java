package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class RoomLists {
    public final String Board;
    public final String Price;
    public final String Title;
    public final int OldPrice;
    public final int EHotelId;
    public final String OfferId;

    public RoomLists(String board, String price, String title, int oldPrice, int EHotelId, String offerId) {
        Board = board;
        Price = price;
        Title = title;
        OldPrice = oldPrice;
        this.EHotelId = EHotelId;
        OfferId = offerId;
    }
}
