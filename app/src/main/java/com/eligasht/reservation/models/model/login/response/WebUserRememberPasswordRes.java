package com.eligasht.reservation.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.eligasht.reservation.models.model.login.LoginResult;

/**
 * Created by elham.bonyani on 1/22/18.
 */

public class WebUserRememberPasswordRes {

    @SerializedName("WebUserRememberPasswordResult")
    @Expose
    private LoginResult WebUserRememberPasswordResult;

    public LoginResult getWebUserRememberPasswordResult() {
        return WebUserRememberPasswordResult;
    }

    public void setWebUserRememberPasswordResult(LoginResult LoginResult) {
        this.WebUserRememberPasswordResult = LoginResult;
    }
}
