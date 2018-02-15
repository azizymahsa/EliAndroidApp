package com.eligasht.reservation.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.eligasht.reservation.models.model.login.LoginResult;

/**
 * Created by elham.bonyani on 1/22/18.
 */

public class WebUserUpdateProfileRes {

    @SerializedName("WebUserUpdateProfileResult")
    @Expose
    private LoginResult WebUserUpdateProfileResult;

    public LoginResult getWebUserUpdateProfilerResult() {
        return WebUserUpdateProfileResult;
    }

    public void setWebUserUpdateProfilerResult(LoginResult LoginResult) {
        this.WebUserUpdateProfileResult = LoginResult;
    }
}
