
package com.eligasht.service.model.newModel.hotel.hotelDetail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelImage {

    @SerializedName("HotelImagesID")
    @Expose
    private Integer hotelImagesID;
    @SerializedName("HotelImagesURL")
    @Expose
    private String hotelImagesURL;
    @SerializedName("HotelImagesDesciption")
    @Expose
    private String hotelImagesDesciption;

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

    public String getHotelImagesDesciption() {
        return hotelImagesDesciption;
    }

    public void setHotelImagesDesciption(String hotelImagesDesciption) {
        this.hotelImagesDesciption = hotelImagesDesciption;
    }

}
