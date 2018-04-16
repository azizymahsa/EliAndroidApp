
package com.eligasht.service.model.addReview.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewScore {

    @SerializedName("HotelID")
    @Expose
    private String hotelID;
    @SerializedName("ReviewID")
    @Expose
    private String reviewID;
    @SerializedName("ScoreAmount")
    @Expose
    private String scoreAmount;
    @SerializedName("ScoreCounts")
    @Expose
    private String scoreCounts;
    @SerializedName("ScoreParameterID")
    @Expose
    private String scoreParameterID;
    @SerializedName("SumScore")
    @Expose
    private String sumScore;

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getScoreAmount() {
        return scoreAmount;
    }

    public void setScoreAmount(String scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    public String getScoreCounts() {
        return scoreCounts;
    }

    public void setScoreCounts(String scoreCounts) {
        this.scoreCounts = scoreCounts;
    }

    public String getScoreParameterID() {
        return scoreParameterID;
    }

    public void setScoreParameterID(String scoreParameterID) {
        this.scoreParameterID = scoreParameterID;
    }

    public String getSumScore() {
        return sumScore;
    }

    public void setSumScore(String sumScore) {
        this.sumScore = sumScore;
    }

}
