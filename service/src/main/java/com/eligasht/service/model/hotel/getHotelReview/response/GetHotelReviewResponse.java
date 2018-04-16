
package com.eligasht.service.model.hotelflight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelReviewResponse {

    @SerializedName("GetHotelReviewResult")
    @Expose
    private GetHotelReviewResult getHotelReviewResult;

    public GetHotelReviewResult getGetHotelReviewResult() {
        return getHotelReviewResult;
    }

    public void setGetHotelReviewResult(GetHotelReviewResult getHotelReviewResult) {
        this.getHotelReviewResult = getHotelReviewResult;
    }

}
