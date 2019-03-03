
package com.eligasht.service.model.newModel.hotel.review.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAddHotelReview {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("HotelReviewModel")
    @Expose
    private HotelReviewModel hotelReviewModel;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public HotelReviewModel getHotelReviewModel() {
        return hotelReviewModel;
    }

    public void setHotelReviewModel(HotelReviewModel hotelReviewModel) {
        this.hotelReviewModel = hotelReviewModel;
    }

}
