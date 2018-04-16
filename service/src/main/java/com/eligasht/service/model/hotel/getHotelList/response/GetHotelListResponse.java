
package com.eligasht.service.model.hotel.getHotelList.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelListResponse {

    @SerializedName("GetHotelListResult")
    @Expose
    private GetHotelListResult getHotelListResult;

    public GetHotelListResult getGetHotelListResult() {
        return getHotelListResult;
    }

    public void setGetHotelListResult(GetHotelListResult getHotelListResult) {
        this.getHotelListResult = getHotelListResult;
    }

}
