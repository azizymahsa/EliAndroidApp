package com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.response;

import com.reserv.myapplicationeli.models.model.Errors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reza.nejati on 2/10/2018.
 */

public class UserEntranceResponse {
    public final MobileAppStartupServiceResult UserEntranceResponse;
    public final String ID;
    public final ArrayList<Errors> errors;

    public UserEntranceResponse(MobileAppStartupServiceResult userEntranceResponse, String ID, ArrayList<Errors> errors) {
        UserEntranceResponse = userEntranceResponse;
        this.ID = ID;
        this.errors = errors;
    }
}
