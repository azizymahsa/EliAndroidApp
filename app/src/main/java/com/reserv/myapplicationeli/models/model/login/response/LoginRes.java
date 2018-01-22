package com.reserv.myapplicationeli.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.login.LoginResult;

/**
 * Created by hoseinraeisi on 1/22/18.
 */

public class LoginRes {

    @SerializedName("LoginResult")
    @Expose
    private com.reserv.myapplicationeli.models.model.login.LoginResult LoginResult;

    public com.reserv.myapplicationeli.models.model.login.LoginResult getLoginResult() {
        return LoginResult;
    }

    public void setLoginResult(com.reserv.myapplicationeli.models.model.login.LoginResult LoginResult) {
        this.LoginResult = LoginResult;
    }
}
