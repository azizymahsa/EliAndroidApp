
package com.eligasht.service.model.hotel.getHotelReview.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelReviewRequest {

    @SerializedName("request")
    @Expose
    private GetHReviewReq request;

    public GetHReviewReq getRequest() {
        return request;
    }

    public void setRequest(GetHReviewReq request) {
        this.request = request;
    }

}
