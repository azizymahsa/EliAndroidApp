
package com.eligasht.service.model.hotel.hold.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoldRoomRequest {

    @SerializedName("request")
    @Expose
    private HoldRoomReq request;

    public HoldRoomReq getRequest() {
        return request;
    }

    public void setRequest(HoldRoomReq request) {
        this.request = request;
    }

}
