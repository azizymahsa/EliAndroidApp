package com.eligasht.service.model.newModel.login.registerUser.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestRegisterUser {
    @SerializedName("UserMobile")
    @Expose
    private String UserMobile;
    @SerializedName("UserPassword")
    @Expose
    private String UserPassword;

    @SerializedName("Culture ")
    @Expose
    private String Culture ;

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }

    public String getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(String userMobile) {
        UserMobile = userMobile;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
