package com.reserv.myapplicationeli.models.hotel.api.getComment.response;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class GetCommentResponse {
    private GetHotelReviewResult GetHotelReviewResult;

    public GetHotelReviewResult getGetHotelReviewResult ()
    {
        return GetHotelReviewResult;
    }

    public void setGetHotelReviewResult (GetHotelReviewResult GetHotelReviewResult)
    {
        this.GetHotelReviewResult = GetHotelReviewResult;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [GetHotelReviewResult = "+GetHotelReviewResult+"]";
    }
}