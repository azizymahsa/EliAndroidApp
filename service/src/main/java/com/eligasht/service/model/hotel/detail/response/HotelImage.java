
package com.eligasht.service.model.hotel.detail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelImage {

    @SerializedName("HotelImagesDesciption")
    @Expose
    private String hotelImagesDesciption;
    @SerializedName("HotelImagesID")
    @Expose
    private Integer hotelImagesID;
    @SerializedName("HotelImagesURL")
    @Expose
    private String hotelImagesURL;

    public String getHotelImagesDesciption() {
        return hotelImagesDesciption;
    }

    public void setHotelImagesDesciption(String hotelImagesDesciption) {
        this.hotelImagesDesciption = hotelImagesDesciption;
    }

    public Integer getHotelImagesID() {
        return hotelImagesID;
    }

    public void setHotelImagesID(Integer hotelImagesID) {
        this.hotelImagesID = hotelImagesID;
    }

    public String getHotelImagesURL() {
        return hotelImagesURL;
    }

    public void setHotelImagesURL(String hotelImagesURL) {
        this.hotelImagesURL = hotelImagesURL;
    }

}
