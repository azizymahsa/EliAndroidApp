package com.eligasht.reservation.models.hotel.api.userEntranceRequest.response;

import java.util.List;

/**
 * Created by Reza.nejati on 2/7/2018.
 */

public class SearchNotes {
    public final List<String> Notes;
    public final String Section;

    public SearchNotes(List<String> notes, String section) {
        Notes = notes;
        Section = section;
    }
}
