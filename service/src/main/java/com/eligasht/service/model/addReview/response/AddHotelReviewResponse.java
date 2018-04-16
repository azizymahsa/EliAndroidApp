
package com.eligasht.service.model.addReview.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddHotelReviewResponse {

    @SerializedName("AddHotelReviewResult")
    @Expose
    private AddHotelReviewResult addHotelReviewResult;

    public AddHotelReviewResult getAddHotelReviewResult() {
        return addHotelReviewResult;
    }

    public void setAddHotelReviewResult(AddHotelReviewResult addHotelReviewResult) {
        this.addHotelReviewResult = addHotelReviewResult;
    }

}
