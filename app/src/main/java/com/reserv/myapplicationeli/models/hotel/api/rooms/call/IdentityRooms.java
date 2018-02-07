package com.reserv.myapplicationeli.models.hotel.api.rooms.call;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class IdentityRooms {
    public final String UserName;
    public final String Password;
    public final String TermianlId;


    public IdentityRooms(String userName, String password, String termianlId) {
        UserName = userName;
        Password = password;
        TermianlId = termianlId;
    }
}
