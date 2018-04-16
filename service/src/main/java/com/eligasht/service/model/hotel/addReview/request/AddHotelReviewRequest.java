
package com.eligasht.service.model.hotel.addReview.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddHotelReviewRequest {

    @SerializedName("request")
    @Expose
    private AddHReviewReq request;

    public AddHReviewReq getRequest() {
        return request;
    }

    public void setRequest(AddHReviewReq request) {
        this.request = request;
    }

}
