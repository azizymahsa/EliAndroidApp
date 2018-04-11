
package com.eligasht.service.model.startup.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartupServiceRequest {

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
