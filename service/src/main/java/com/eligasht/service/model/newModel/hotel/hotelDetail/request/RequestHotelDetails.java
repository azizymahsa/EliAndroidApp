package com.eligasht.service.model.newModel.hotel.hotelDetail.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHotelDetails {

    @SerializedName("hotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("culture")
    @Expose
    private String culture;

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }
}
