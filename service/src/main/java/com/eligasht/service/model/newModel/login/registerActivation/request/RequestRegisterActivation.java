package com.eligasht.service.model.newModel.login.registerActivation.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestRegisterActivation {
    @SerializedName("UserMobile")
    @Expose
    private String UserMobile;
    @SerializedName("ActivationCode")
    @Expose
    private String ActivationCode;

    @SerializedName("Culture ")
    @Expose
    private String Culture ;

    public String getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(String userMobile) {
        UserMobile = userMobile;
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
