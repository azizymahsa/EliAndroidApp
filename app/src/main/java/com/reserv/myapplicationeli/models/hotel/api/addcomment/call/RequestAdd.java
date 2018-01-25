package com.reserv.myapplicationeli.models.hotel.api.addcomment.call;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class RequestAdd {
    public final Identity identity;
    public final String Culture;
    public final ReviewComment ReviewComment;


    public RequestAdd(Identity identity, String culture, com.reserv.myapplicationeli.models.hotel.api.addcomment.call.ReviewComment reviewComment) {
        this.identity = identity;
        Culture = culture;
        ReviewComment = reviewComment;
    }
}
