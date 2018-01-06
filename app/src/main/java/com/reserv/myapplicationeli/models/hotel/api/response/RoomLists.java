package com.reserv.myapplicationeli.models.hotel.api.response;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class RoomLists {
    public final String Board;
    public final String Price;
    public final String Title;
    public final int OldPrice;

    public RoomLists(String board, String price, String title, int oldPrice) {
        Board = board;
        Price = price;
        Title = title;
        OldPrice = oldPrice;
    }
}
