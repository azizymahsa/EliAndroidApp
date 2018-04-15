
package com.eligasht.service.model.login.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class LoginRequestModel {

    @SerializedName("request")
    @Expose
    private LoginListReq request;

    public LoginListReq getRequest() {
        return request;
    }

    public void setRequest(LoginListReq request) {
        this.request = request;
    }

    public LoginRequestModel(LoginListReq request) {
        this.request = request;
    }
}
