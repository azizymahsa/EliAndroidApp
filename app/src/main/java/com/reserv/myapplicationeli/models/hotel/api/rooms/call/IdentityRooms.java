package com.reserv.myapplicationeli.models.hotel.api.rooms.call;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class IdentityRooms {
    public final String Password;
    public final String UserName;
    public final String TermianlId;

    public IdentityRooms(String password, String userName, String termianlId) {
        Password = password;
        UserName = userName;
        TermianlId = termianlId;
    }
}
