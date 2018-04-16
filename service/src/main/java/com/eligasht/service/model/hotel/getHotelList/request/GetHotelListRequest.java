
package com.eligasht.service.model.hotel.getHotelList.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelListRequest {

    @SerializedName("request")
    @Expose
    private GetHListRequest request;

    public GetHListRequest getRequest() {
        return request;
    }

    public void setRequest(GetHListRequest request) {
        this.request = request;
    }

}
