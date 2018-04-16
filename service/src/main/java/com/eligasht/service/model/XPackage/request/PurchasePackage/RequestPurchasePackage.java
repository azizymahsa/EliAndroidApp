
package com.eligasht.service.model.XPackage.request.PurchasePackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPurchasePackage {

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
