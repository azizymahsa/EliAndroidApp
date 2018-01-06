package com.reserv.myapplicationeli.views.adapters.hotel.rooms;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class RoomsModel {
    public String board;
    public String title;
    public String desc;
    public String price;

    public RoomsModel(String board, String title, String desc, String price) {
        this.board = board;
        this.title = title;
        this.desc = desc;
        this.price = price;
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
}
