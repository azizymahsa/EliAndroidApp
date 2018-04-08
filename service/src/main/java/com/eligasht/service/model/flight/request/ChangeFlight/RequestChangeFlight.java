
package com.eligasht.service.model.flight.request.ChangeFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestChangeFlight {

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
