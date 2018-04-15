
package com.eligasht.service.model.hotel.room.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRoomRequest {

    @SerializedName("request")
    @Expose
    private GetRoomReq request;

    public GetRoomReq getRequest() {
        return request;
    }

    public void setRequest(GetRoomReq request) {
        this.request = request;
    }

}
