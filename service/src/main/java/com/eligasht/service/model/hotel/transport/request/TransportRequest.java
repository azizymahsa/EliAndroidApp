
package com.eligasht.service.model.hotel.transport.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransportRequest {

    @SerializedName("request")
    @Expose
    private TransportReq request;

    public TransportReq getRequest() {
        return request;
    }

    public void setRequest(TransportReq request) {
        this.request = request;
    }

}
