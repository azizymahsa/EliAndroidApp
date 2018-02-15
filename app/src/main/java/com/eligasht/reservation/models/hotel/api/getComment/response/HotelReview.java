package com.eligasht.reservation.models.hotel.api.getComment.response;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class HotelReview {
    public final String HotelName;

    public final String HotelID;

    public final AverageReviewScores[] AverageReviewScores;

    public final String ReviewsCount;

    public final Reviews[] Reviews;

    public final ReviewFilterItems[] ReviewFilterItems;

    public final String RecommendedPercent;


    public final String AverageScore;

    public HotelReview(String hotelName, String hotelID, com.eligasht.reservation.models.hotel.api.getComment.response.AverageReviewScores[] averageReviewScores, String reviewsCount, com.eligasht.reservation.models.hotel.api.getComment.response.Reviews[] reviews, com.eligasht.reservation.models.hotel.api.getComment.response.ReviewFilterItems[] reviewFilterItems, String recommendedPercent, String averageScore) {
        HotelName = hotelName;
        HotelID = hotelID;
        AverageReviewScores = averageReviewScores;
        ReviewsCount = reviewsCount;
        Reviews = reviews;
        ReviewFilterItems = reviewFilterItems;
        RecommendedPercent = recommendedPercent;
        AverageScore = averageScore;
    }
}

