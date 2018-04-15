
package com.eligasht.service.model.hotelpolicy.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelPolicyRequest {

    @SerializedName("request")
    @Expose
    private HotelPolicySubRequest request;

    public HotelPolicySubRequest getRequest() {
        return request;
    }

    public void setRequest(HotelPolicySubRequest request) {
        this.request = request;
    }

}
