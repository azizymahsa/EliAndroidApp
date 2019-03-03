
package com.eligasht.service.model.newModel.hotel.review.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewScore {

    @SerializedName("ScoreParameterID")
    @Expose
    private Integer scoreParameterID;
    @SerializedName("ScoreCounts")
    @Expose
    private Integer scoreCounts;
    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("SumScore")
    @Expose
    private Integer sumScore;
    @SerializedName("ScoreAmount")
    @Expose
    private Integer scoreAmount;
    @SerializedName("ReviewID")
    @Expose
    private Integer reviewID;

    public ReviewScore(Integer scoreParameterID, Integer scoreCounts, Integer hotelID, Integer sumScore, Integer scoreAmount, Integer reviewID) {
        this.scoreParameterID = scoreParameterID;
        this.scoreCounts = scoreCounts;
        this.hotelID = hotelID;
        this.sumScore = sumScore;
        this.scoreAmount = scoreAmount;
        this.reviewID = reviewID;
    }

    public Integer getScoreParameterID() {
        return scoreParameterID;
    }

    public void setScoreParameterID(Integer scoreParameterID) {
        this.scoreParameterID = scoreParameterID;
    }

    public Integer getScoreCounts() {
        return scoreCounts;
    }

    public void setScoreCounts(Integer scoreCounts) {
        this.scoreCounts = scoreCounts;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getSumScore() {
        return sumScore;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    public Integer getScoreAmount() {
        return scoreAmount;
    }

    public void setScoreAmount(Integer scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

}
