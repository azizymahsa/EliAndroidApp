
package com.eligasht.service.model.hotelflight.purchase.request.PishFactor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPurchaseService {

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
