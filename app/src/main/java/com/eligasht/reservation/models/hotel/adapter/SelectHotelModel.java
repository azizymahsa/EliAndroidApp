package com.eligasht.reservation.models.hotel.adapter;


import com.eligasht.service.model.newModel.hotel.search.response.Facility;
import com.eligasht.service.model.newModel.hotel.search.response.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reza.nejati on 1/4/2018.
 */

public class SelectHotelModel {
    private String name;
    private String city;
    private String title;
    private String board;
;
    private String imageUrl;
    private String location;
    Double price;
    Double oldPrice;
    int star;
    private int eHotelId;
    private String ResultUniqID;
    boolean bestSell;
    boolean isOff;
    String off;
    String TypeText;
    List<Facility> Facilities;
    int diff;
    String offerId;
    List<Location> Locations;

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    String CurrencyCode;
    public SelectHotelModel(String name, String city, String title, String board, Double price, String imageUrl, String location, Double oldPrice, int star, int eHotelId, String resultUniqID, boolean bestSell, boolean isOff, String off, String typeText, List<Facility> facilities, int diff, String offerId, List<com.eligasht.service.model.newModel.hotel.search.response.Location> locations,String CurrencyCode) {
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
        this.offerId = offerId;
        Locations = locations;
        this.CurrencyCode = CurrencyCode;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
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


    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public List<Facility> getFacilities() {
        return Facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        Facilities = facilities;
    }

    public List<Location> getLocations() {
        return Locations;
    }

    public void setLocations(List<Location> locations) {
        Locations = locations;
    }
}