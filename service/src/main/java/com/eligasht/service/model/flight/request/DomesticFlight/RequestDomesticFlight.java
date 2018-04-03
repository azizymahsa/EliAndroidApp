
package com.eligasht.service.model.flight.request.DomesticFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDomesticFlight {

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
