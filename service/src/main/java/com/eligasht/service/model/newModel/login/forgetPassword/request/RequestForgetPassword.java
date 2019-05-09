package com.eligasht.service.model.newModel.login.forgetPassword.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestForgetPassword {
    @SerializedName("UserMobile")
    @Expose
    private String userMobile;


    @SerializedName("Culture")
    @Expose
    private String Culture;

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }
}
