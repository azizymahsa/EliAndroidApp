
package com.eligasht.service.model.insurance.request.RequestPreFactorDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPreFactorDetails {

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
