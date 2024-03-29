package com.eligasht.service.model.newModel.login.reSendActivation.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestReSendActivation {
    @SerializedName("UserMobile")
    @Expose
    private String UserMobile;


    @SerializedName("Culture ")
    @Expose
    private String Culture ;

    public String getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(String userMobile) {
        UserMobile = userMobile;
    }

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }
}
