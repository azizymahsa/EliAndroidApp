package com.eligasht.reservation.models.hotel.api.getComment.response;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class GetCommentResponse {
    public final GetHotelReviewResult GetHotelReviewResult;

    public GetCommentResponse(com.eligasht.reservation.models.hotel.api.getComment.response.GetHotelReviewResult getHotelReviewResult) {
        GetHotelReviewResult = getHotelReviewResult;
    }
}