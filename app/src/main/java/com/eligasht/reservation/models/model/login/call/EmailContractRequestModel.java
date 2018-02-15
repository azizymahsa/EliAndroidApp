
package com.eligasht.reservation.models.model.login.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailContractRequestModel {

    @SerializedName("request")
    @Expose
    private EmailContractReq request;

    public EmailContractReq getRequest() {
        return request;
    }

    public void setRequest(EmailContractReq request) {
        this.request = request;
    }

    public EmailContractRequestModel(EmailContractReq request) {
        this.request = request;
    }
}
