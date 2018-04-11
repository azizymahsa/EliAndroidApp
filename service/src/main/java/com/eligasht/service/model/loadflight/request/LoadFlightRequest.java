
package com.eligasht.service.model.loadflight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadFlightRequest {

    @SerializedName("request")
    @Expose
    private LoadFlightSubRequest request;

    public LoadFlightSubRequest getRequest() {
        return request;
    }

    public void setRequest(LoadFlightSubRequest request) {
        this.request = request;
    }

}
