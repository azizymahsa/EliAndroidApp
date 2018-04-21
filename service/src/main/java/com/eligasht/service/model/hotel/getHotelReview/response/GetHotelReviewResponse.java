
package com.eligasht.service.model.hotel.getHotelReview.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelReviewResponse {

    @SerializedName("GetHotelReviewResult")
    @Expose
    public GetHotelReviewResult getHotelReviewResult;

    public GetHotelReviewResult getGetHotelReviewResult() {
        return getHotelReviewResult;
    }

    public void setGetHotelReviewResult(GetHotelReviewResult getHotelReviewResult) {
        this.getHotelReviewResult = getHotelReviewResult;
    }

}
