
package com.eligasht.service.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("LoginResult")
    @Expose
    private LoginResult loginResult;

    public LoginResult getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }

}
