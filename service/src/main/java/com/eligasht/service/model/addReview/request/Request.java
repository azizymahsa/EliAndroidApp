
package com.eligasht.service.model.addReview.request;

import com.eligasht.service.model.identity.Identity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("HotelReviewModel")
    @Expose
    private HotelReviewModel hotelReviewModel;
    @SerializedName("identity")
    @Expose
    private Identity identity;

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

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
