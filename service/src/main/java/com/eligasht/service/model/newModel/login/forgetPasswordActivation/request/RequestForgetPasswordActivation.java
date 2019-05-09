package com.eligasht.service.model.newModel.login.forgetPasswordActivation.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestForgetPasswordActivation {
    @SerializedName("UserMobile")
    @Expose
    private String userMobile;
    @SerializedName("ActivationCode")
    @Expose
    private String ActivationCode;

    @SerializedName("Culture")
    @Expose
    private String Culture;

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getActivationCode() {
        return ActivationCode;
    }

    public void setActivationCode(String activationCode) {
        ActivationCode = activationCode;
    }

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }
}
