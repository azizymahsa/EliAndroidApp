package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class Identity {
    public final String UserName;
    public final String Password;
    public final String TermianlId;
    public final String RequestorID;

    public Identity(String userName, String password, String termianlId, String requestorID) {
        UserName = userName;
        Password = password;
        TermianlId = termianlId;
        RequestorID = requestorID;
    }
}
