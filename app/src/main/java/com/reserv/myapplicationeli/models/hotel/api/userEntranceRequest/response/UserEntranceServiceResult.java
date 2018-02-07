package com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.response;

import android.widget.ListView;

import java.util.List;

/**
 * Created by Reza.nejati on 2/4/2018.
 */

public class UserEntranceServiceResult {
    public final boolean CanEnter;
    public final String MinAppVersion;
    public final List<SearchNotes> SearchNotes;

    public UserEntranceServiceResult(boolean canEnter, String minAppVersion, List<SearchNotes> searchNotes) {
        CanEnter = canEnter;
        MinAppVersion = minAppVersion;
        SearchNotes = searchNotes;
    }
}
