
package com.eligasht.reservation.models.model.insurance.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsuranceRequestModel {

    @SerializedName("request")
    @Expose
    private InsuranceListReq request;

    public InsuranceRequestModel(InsuranceListReq request) {
        this.request = request;
    }

    public InsuranceListReq getRequest() {
        return request;
    }

    public void setRequest(InsuranceListReq request) {
        this.request = request;
    }

}
