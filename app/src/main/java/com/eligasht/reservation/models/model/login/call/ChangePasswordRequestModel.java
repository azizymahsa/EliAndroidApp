
package com.eligasht.reservation.models.model.login.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequestModel {

    @SerializedName("request")
    @Expose
    private ChangePasswordReq request;

    public ChangePasswordReq getRequest() {
        return request;
    }

    public void setRequest(ChangePasswordReq request) {
        this.request = request;
    }

    public ChangePasswordRequestModel(ChangePasswordReq request) {
        this.request = request;
    }
}
