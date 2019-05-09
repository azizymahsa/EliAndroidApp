package com.eligasht.service.model.newModel.login.forgetPasswordChangePassword.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestForgetPasswordChangePassword {

    @SerializedName("ActivationCode")
    @Expose
    private String activationCode;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("EUserId")
    @Expose
    private String eUserId;
    @SerializedName("UserMobile")
    @Expose
    private String userMobile;
    @SerializedName("UserPassword")
    @Expose
    private String userPassword;

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getEUserId() {
        return eUserId;
    }

    public void setEUserId(String eUserId) {
        this.eUserId = eUserId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}