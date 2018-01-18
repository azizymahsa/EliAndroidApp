package com.reserv.myapplicationeli.models.hotel.adapter;

import com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest.FltList;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Facilities;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/4/2018.
 */

public class SelectFlightHotelModel {
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
    boolean isOff;
    String off;
    String TypeText;
    ArrayList<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Facilities> Facilities;
    int diff;
     ArrayList<FltList> Flights;
     String ArrRout;
     String DepRout;
     String Amount;

    public SelectFlightHotelModel(String name, String city, String title, String board, String price,
                                  String imageUrl, String location, int oldPrice, int star, int eHotelId,
                                  String resultUniqID, boolean bestSell, boolean isOff, String off, String typeText
            , ArrayList<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Facilities> facilities, int diff,
                                  ArrayList<FltList> flights, String arrRout, String depRout, String amount) {
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
        this.isOff = isOff;
        this.off = off;
        TypeText = typeText;
        Facilities = facilities;
        this.diff = diff;
        Flights = flights;
        ArrRout = arrRout;
        DepRout = depRout;
        Amount = amount;
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

    public boolean isOff() {
        return isOff;
    }

    public void setOff(boolean off) {
        isOff = off;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    public String getTypeText() {
        return TypeText;
    }

    public void setTypeText(String typeText) {
        TypeText = typeText;
    }

    public ArrayList<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Facilities> getFacilities() {
        return Facilities;
    }

    public void setFacilities(ArrayList<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Facilities> facilities) {
        Facilities = facilities;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public ArrayList<FltList> getFlights() {
        return Flights;
    }

    public void setFlights(ArrayList<FltList> flights) {
        Flights = flights;
    }

    public String getArrRout() {
        return ArrRout;
    }

    public void setArrRout(String arrRout) {
        ArrRout = arrRout;
    }

    public String getDepRout() {
        return DepRout;
    }

    public void setDepRout(String depRout) {
        DepRout = depRout;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}