
package com.eligasht.service.model.hotelflight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewScore {

    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("ParamTitle")
    @Expose
    private String paramTitle;
    @SerializedName("ReviewID")
    @Expose
    private Integer reviewID;
    @SerializedName("ScoreAmount")
    @Expose
    private Integer scoreAmount;
    @SerializedName("ScoreCounts")
    @Expose
    private Integer scoreCounts;
    @SerializedName("ScoreParameterID")
    @Expose
    private Integer scoreParameterID;
    @SerializedName("SumScore")
    @Expose
    private Integer sumScore;

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getParamTitle() {
        return paramTitle;
    }

    public void setParamTitle(String paramTitle) {
        this.paramTitle = paramTitle;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getScoreAmount() {
        return scoreAmount;
    }

    public void setScoreAmount(Integer scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    public Integer getScoreCounts() {
        return scoreCounts;
    }

    public void setScoreCounts(Integer scoreCounts) {
        this.scoreCounts = scoreCounts;
    }

    public Integer getScoreParameterID() {
        return scoreParameterID;
    }

    public void setScoreParameterID(Integer scoreParameterID) {
        this.scoreParameterID = scoreParameterID;
    }

    public Integer getSumScore() {
        return sumScore;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

}
