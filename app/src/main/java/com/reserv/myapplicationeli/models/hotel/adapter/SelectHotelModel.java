package com.reserv.myapplicationeli.models.hotel.adapter;

/**
 * Created by Reza.nejati on 1/4/2018.
 */

public class SelectHotelModel {
    private String name;
    private String city;
    private String title;
    private String board;
    private String price;
    private String imageUrl;
    private String location;

    int oldPrice;
    int star;
    private int eHotelId;
    private String ResultUniqID;
    boolean bestSell;

    public SelectHotelModel(String name, String city, String title, String board, String price,
                            String imageUrl, String location, int oldPrice, int star, int eHotelId, String resultUniqID, boolean bestSell) {
        this.name = name;
        this.city = city;
        this.title = title;
        this.board = board;
        this.price = price;
        this.imageUrl = imageUrl;
        this.location = location;
        this.oldPrice = oldPrice;
        this.star = star;
        this.eHotelId = eHotelId;
        ResultUniqID = resultUniqID;
        this.bestSell = bestSell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int geteHotelId() {
        return eHotelId;
    }

    public void seteHotelId(int eHotelId) {
        this.eHotelId = eHotelId;
    }

    public String getResultUniqID() {
        return ResultUniqID;
    }

    public void setResultUniqID(String resultUniqID) {
        ResultUniqID = resultUniqID;
    }

    public boolean isBestSell() {
        return bestSell;
    }

    public void setBestSell(boolean bestSell) {
        this.bestSell = bestSell;
    }
}