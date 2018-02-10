package com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.response;

import java.util.List;

/**
 * Created by Reza.nejati on 2/10/2018.
 */

public class MobileAppStartupServiceResult {
    public final String MinAppVersion;
    public final boolean CanEnter;

    public final List<SearchNotes> SearchNotes;

    public MobileAppStartupServiceResult(String minAppVersion, boolean canEnter, List<com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.response.SearchNotes> searchNotes) {
        MinAppVersion = minAppVersion;
        CanEnter = canEnter;
        SearchNotes = searchNotes;
    }
}
