package com.eligasht.reservation.models.hotel.api.addcomment.response;

import com.eligasht.reservation.models.hotel.api.Errors;

import java.util.List;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class AddHotelReviewCommentsResult {
    public final List<Errors> Errors;
    public final String ResultText;

    public AddHotelReviewCommentsResult(List<com.eligasht.reservation.models.hotel.api.Errors> errors, String resultText) {
        Errors = errors;
        ResultText = resultText;
    }
}
