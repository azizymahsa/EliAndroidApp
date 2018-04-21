
package com.eligasht.service.model.hotel.detail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelDetailResponse {

    @SerializedName("GetHotelDetailResult")
    @Expose
    public GetHotelDetailResult getHotelDetailResult;

    public GetHotelDetailResult getGetHotelDetailResult() {
        return getHotelDetailResult;
    }

    public void setGetHotelDetailResult(GetHotelDetailResult getHotelDetailResult) {
        this.getHotelDetailResult = getHotelDetailResult;
    }

}
