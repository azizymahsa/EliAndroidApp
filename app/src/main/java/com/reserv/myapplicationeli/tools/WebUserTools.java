package com.reserv.myapplicationeli.tools;

import com.reserv.myapplicationeli.models.model.login.WebUser;

/**
 * Created by elham.bonyani on 1/22/18.
 */

public class WebUserTools {

    private static WebUserTools instance;
    private static String web_user_key = "WEB_USER";
    private static WebUser user;
    public static WebUserTools getInstance() {
        if(instance == null){
            instance = new WebUserTools();
        }
        if(Prefs.getObject(web_user_key,WebUser.class) == null){
            user = new WebUser();
            Prefs.putObject(web_user_key,user);
        }else{
            user = Prefs.getObject(web_user_key,WebUser.class);
        }
        return instance;
    }

    private WebUserTools() {
    }
    public final WebUser getUser()
    {
        return user;
    }
    public final void setUser(WebUser value)
    {
        Prefs.putObject(web_user_key,value);
    }


}
