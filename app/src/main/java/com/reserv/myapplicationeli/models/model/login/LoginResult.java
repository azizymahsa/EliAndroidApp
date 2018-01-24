package com.reserv.myapplicationeli.models.model.login;

import com.reserv.myapplicationeli.models.model.Errors;

import java.util.ArrayList;

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

    private ArrayList<Errors> Error;
    public final ArrayList<Errors> getError()
    {
        return Error;
    }
    public final void setError(ArrayList<Errors> value)
    {
        Error = value;
    }

}
