
package com.reserv.myapplicationeli.models.model.pack.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseRequestModel {

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
