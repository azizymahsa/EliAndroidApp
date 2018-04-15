
package com.eligasht.service.model.hotel.detail.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelDetailRequest {

    @SerializedName("request")
    @Expose
    private HotelDetailReq request;

    public HotelDetailReq getRequest() {
        return request;
    }

    public void setRequest(HotelDetailReq request) {
        this.request = request;
    }

}
