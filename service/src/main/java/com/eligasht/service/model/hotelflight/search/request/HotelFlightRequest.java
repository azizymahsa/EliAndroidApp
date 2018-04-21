
package com.eligasht.service.model.hotelflight.search.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelFlightRequest {

    @SerializedName("request")
    @Expose
    private HotelFlightSubRequest request;

    public HotelFlightSubRequest getRequest() {
        return request;
    }

    public void setRequest(HotelFlightSubRequest request) {
        this.request = request;
    }

}
