package com.reserv.myapplicationeli.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hoseinraeisi on 1/22/18.
 */

public class WebUserRememberPasswordRes {

    @SerializedName("WebUserRememberPasswordResult")
    @Expose
    private com.reserv.myapplicationeli.models.model.login.LoginResult WebUserRememberPasswordResult;

    public com.reserv.myapplicationeli.models.model.login.LoginResult getWebUserRememberPasswordResult() {
        return WebUserRememberPasswordResult;
    }

    public void setWebUserRememberPasswordResult(com.reserv.myapplicationeli.models.model.login.LoginResult LoginResult) {
        this.WebUserRememberPasswordResult = LoginResult;
    }
}
