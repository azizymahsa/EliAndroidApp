package com.eligasht.reservation.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.eligasht.reservation.models.model.login.LoginResult;

/**
 * Created by elham.bonyani on 1/22/18.
 */

public class WebUserRegisterRes {

    @SerializedName("WebUserRegisterResult")
    @Expose
    private LoginResult WebUserRegisterResult;

    public LoginResult getWebUserRegisterResult() {
        return WebUserRegisterResult;
    }

    public void setWebUserRegisterResult(LoginResult LoginResult) {
        this.WebUserRegisterResult = LoginResult;
    }
}
