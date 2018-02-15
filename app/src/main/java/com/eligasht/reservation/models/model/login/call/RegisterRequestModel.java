
package com.eligasht.reservation.models.model.login.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequestModel {

    @SerializedName("request")
    @Expose
    private RegisterListReq request;

    public RegisterListReq getRequest() {
        return request;
    }

    public void setRequest(RegisterListReq request) {
        this.request = request;
    }

    public RegisterRequestModel (RegisterListReq request){
        this.request = request;
    }

}
