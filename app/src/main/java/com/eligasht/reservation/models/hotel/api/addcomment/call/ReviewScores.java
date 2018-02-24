package com.eligasht.reservation.models.hotel.api.addcomment.call;

/**
 * Created by Reza.nejati on 2/24/2018.
 */

public class ReviewScores {
    public final String HotelID;
    public final String ReviewID;
    public final String ScoreAmount;
    public final String ScoreCounts;
    public final String ScoreParameterID;
    public final String SumScore;

    public ReviewScores(String hotelID, String reviewID, String scoreAmount, String scoreCounts, String scoreParameterID, String sumScore) {
        HotelID = hotelID;
        ReviewID = reviewID;
        ScoreAmount = scoreAmount;
        ScoreCounts = scoreCounts;
        ScoreParameterID = scoreParameterID;
        SumScore = sumScore;
    }
}
