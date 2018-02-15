package com.eligasht.reservation.models.hotel.api.addcomment.response;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class AddCommentsResult {
    public final AddHotelReviewCommentsResult AddHotelReviewCommentsResult;

    public AddCommentsResult(com.eligasht.reservation.models.hotel.api.addcomment.response.AddHotelReviewCommentsResult addHotelReviewCommentsResult) {
        AddHotelReviewCommentsResult = addHotelReviewCommentsResult;
    }
}
