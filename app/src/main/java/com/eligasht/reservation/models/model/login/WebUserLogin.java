package com.eligasht.reservation.models.model.login;



import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/20/2018.
 */

public class WebUserLogin {
    private String LoginStatus;
    public final String getLoginStatus()
    {
        return LoginStatus;
    }
    public final void setLoginStatus(String value)
    {
        LoginStatus = value;
    }

    private ArrayList<Contract> PreviousContracts;
    public final ArrayList<Contract> getPreviousContracts()
    {
        return PreviousContracts;
    }
    public final void setPreviousContracts(ArrayList<Contract> value)
    {
        PreviousContracts = value;
    }

    private WebUser WebUserProperties;
    public final WebUser getWebUserProperties()
    {
        return WebUserProperties;
    }
    public final void setWebUserProperties(WebUser value)
    {
        WebUserProperties = value;
    }

}
