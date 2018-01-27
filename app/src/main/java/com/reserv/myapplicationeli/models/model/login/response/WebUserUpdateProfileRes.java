package com.reserv.myapplicationeli.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by elham.bonyani on 1/22/18.
 */

public class WebUserUpdateProfileRes {

    @SerializedName("WebUserUpdateProfileResult")
    @Expose
    private com.reserv.myapplicationeli.models.model.login.LoginResult WebUserUpdateProfileResult;

    public com.reserv.myapplicationeli.models.model.login.LoginResult getWebUserUpdateProfilerResult() {
        return WebUserUpdateProfileResult;
    }

    public void setWebUserUpdateProfilerResult(com.reserv.myapplicationeli.models.model.login.LoginResult LoginResult) {
        this.WebUserUpdateProfileResult = LoginResult;
    }
}
