package com.eligasht.reservation.tools;

import com.eligasht.reservation.models.model.login.Contract;
import com.eligasht.reservation.models.model.login.WebUser;
import com.eligasht.reservation.models.model.login.WebUserLogin;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/22/18.
 */

public class WebUserTools {

    private static WebUserTools instance;
    private static String web_user_key = "WEB_USER";
    private static WebUserLogin user;
    public static WebUserTools getInstance() {
        if(instance == null){
            instance = new WebUserTools();
        }
        if(Prefs.getObject(web_user_key,WebUserLogin.class) == null){
            user = new WebUserLogin();
            user.setWebUserProperties(new WebUser());
            user.setPreviousContracts(new ArrayList<Contract>());
            Prefs.putObject(web_user_key,user);
        }else{
            user = Prefs.getObject(web_user_key,WebUserLogin.class);
        }
        return instance;
    }

    private WebUserTools() {
    }
    public final WebUserLogin getUser()
    {
        return user;
    }
    public final void setUser(WebUserLogin value)
    {
        Prefs.putObject(web_user_key,value);
    }


}
