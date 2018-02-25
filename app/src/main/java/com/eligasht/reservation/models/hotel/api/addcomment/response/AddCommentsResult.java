package com.eligasht.reservation.models.hotel.api.addcomment.response;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class AddCommentsResult {
    public final AddHotelReviewCommentsResult AddHotelReviewResult;

    public AddCommentsResult(AddHotelReviewCommentsResult addHotelReviewResult) {
        AddHotelReviewResult = addHotelReviewResult;
    }
}
