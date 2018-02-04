package com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.response;

/**
 * Created by Reza.nejati on 2/4/2018.
 */

public class UserEntranceServiceResult {
    public final boolean CanEnter;
    public final String MinAppVersion;

    public UserEntranceServiceResult(boolean canEnter, String minAppVersion) {
        CanEnter = canEnter;
        MinAppVersion = minAppVersion;
    }
}
