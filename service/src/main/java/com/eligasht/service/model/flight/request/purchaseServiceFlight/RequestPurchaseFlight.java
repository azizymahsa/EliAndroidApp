
package com.eligasht.service.model.flight.request.purchaseServiceFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPurchaseFlight {

    @SerializedName("request")
    @Expose
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

}
