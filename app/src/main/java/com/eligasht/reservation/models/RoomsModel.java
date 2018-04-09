package com.eligasht.reservation.models;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class RoomsModel {
    public String board;
    public String title;
    public String desc;
    public String price;
    public String offerId;
    public String hotelId;
    public String SearchKey;

    public RoomsModel(String board, String title, String desc, String price, String offerId, String hotelId, String searchKey) {
        this.board = board;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.offerId = offerId;
        this.hotelId = hotelId;
        SearchKey = searchKey;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getSearchKey() {
        return SearchKey;
    }

    public void setSearchKey(String searchKey) {
        SearchKey = searchKey;
    }
}
