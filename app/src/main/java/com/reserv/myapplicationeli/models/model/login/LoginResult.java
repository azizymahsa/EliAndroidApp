package com.reserv.myapplicationeli.models.model.login;

import com.reserv.myapplicationeli.models.model.login.WebUserLogin;

import org.json.JSONObject;

/**
 * Created by elham.bonyani on 1/20/2018.
 */

public class LoginResult {
    private com.reserv.myapplicationeli.models.model.login.WebUserLogin WebUserLogin;
    public final WebUserLogin getWebUserLogin()
    {
        return WebUserLogin;
    }
    public final void setWebUserLogin(WebUserLogin value)
    {
        WebUserLogin = value;
    }

    private JSONObject Error;
    public final JSONObject getError()
    {
        return Error;
    }
    public final void setError(JSONObject value)
    {
        Error = value;
    }

}
