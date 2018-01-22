package com.reserv.myapplicationeli.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hoseinraeisi on 1/22/18.
 */

public class WebUserRegisterRes {

    @SerializedName("WebUserRegisterResult")
    @Expose
    private com.reserv.myapplicationeli.models.model.login.LoginResult WebUserRegisterResult;

    public com.reserv.myapplicationeli.models.model.login.LoginResult getWebUserRegisterResult() {
        return WebUserRegisterResult;
    }

    public void setWebUserRegisterResult(com.reserv.myapplicationeli.models.model.login.LoginResult LoginResult) {
        this.WebUserRegisterResult = LoginResult;
    }
}
