
package com.eligasht.service.model.hotelflight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelFlightRequest {

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
