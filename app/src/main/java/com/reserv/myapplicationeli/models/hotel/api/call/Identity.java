package com.reserv.myapplicationeli.models.hotel.api.call;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class Identity {
    public final String UserName;
    public final String Password;
    public final String TermianlId;

    public Identity(String userName, String password, String termianlId) {
        UserName = userName;
        Password = password;
        TermianlId = termianlId;
    }
}
