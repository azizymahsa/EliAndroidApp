package com.eligasht.reservation.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by elham.bonyani on 1/22/18.
 */

public class LoginRes {

    @SerializedName("LoginResult")
    @Expose
    private com.eligasht.reservation.models.model.login.LoginResult LoginResult;

    public com.eligasht.reservation.models.model.login.LoginResult getLoginResult() {
        return LoginResult;
    }

    public void setLoginResult(com.eligasht.reservation.models.model.login.LoginResult LoginResult) {
        this.LoginResult = LoginResult;
    }
}
