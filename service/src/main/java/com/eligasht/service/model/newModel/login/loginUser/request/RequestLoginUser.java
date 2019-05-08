
package com.eligasht.service.model.newModel.login.loginUser.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLoginUser {

    @SerializedName("UserMobile")
    @Expose
    private String userMobile;
    @SerializedName("UserPassword")
    @Expose
    private String userPassword;

    @SerializedName("Culture")
    @Expose
    private String Culture;


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

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }
}
