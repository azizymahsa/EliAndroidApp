
package com.eligasht.service.model.hotelflight.purchase.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelFlightPurchaseRequest {

    @SerializedName("request")
    @Expose
    private RequestHFPur request;

    public RequestHFPur getRequest() {
        return request;
    }

    public void setRequest(RequestHFPur request) {
        this.request = request;
    }

}
