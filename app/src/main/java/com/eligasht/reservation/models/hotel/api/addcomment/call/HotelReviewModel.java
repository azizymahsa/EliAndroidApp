package com.eligasht.reservation.models.hotel.api.addcomment.call;

/**
 * Created by Reza.nejati on 2/24/2018.
 */

public class HotelReviewModel {
    public final ReviewComment Reviews;
    public final String AverageScore;
    public final String HotelID;
    public final String RecommendedPercent;

    public HotelReviewModel(ReviewComment reviews, String averageScore, String hotelID, String recommendedPercent) {
        Reviews = reviews;
        AverageScore = averageScore;
        HotelID = hotelID;
        RecommendedPercent = recommendedPercent;
    }
}
