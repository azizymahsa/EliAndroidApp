package com.eligasht.reservation.models.hotel.api.addcomment.response;

import com.eligasht.reservation.models.hotel.api.Errors;

import java.util.List;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class AddHotelReviewCommentsResult {
    public final List<Errors> errors;
    public final String ResultText;

    public AddHotelReviewCommentsResult(List<Errors> errors, String resultText) {
        this.errors = errors;
        ResultText = resultText;
    }
}
