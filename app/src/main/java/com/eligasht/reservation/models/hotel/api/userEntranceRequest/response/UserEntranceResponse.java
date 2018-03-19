package com.eligasht.reservation.models.hotel.api.userEntranceRequest.response;

import com.eligasht.reservation.models.model.Errors;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 2/10/2018.
 */

public class UserEntranceResponse {
    public final MobileAppStartupServiceResult UserEntranceResponse;
    public final String ID;
    public final ArrayList<Errors> errors;
    public final boolean AdjustEnabled;
    public final String CultureDefault;

    public UserEntranceResponse(MobileAppStartupServiceResult userEntranceResponse, String ID, ArrayList<Errors> errors, boolean adjustEnabled, String cultureDefault) {
        UserEntranceResponse = userEntranceResponse;
        this.ID = ID;
        this.errors = errors;
        AdjustEnabled = adjustEnabled;
        CultureDefault = cultureDefault;
    }
}
