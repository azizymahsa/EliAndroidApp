
package com.eligasht.service.model.newModel.hotel.hotelDetail.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelReviews {

    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("AverageScore")
    @Expose
    private Integer averageScore;
    @SerializedName("ReviewsCount")
    @Expose
    private Integer reviewsCount;
    @SerializedName("RecommendedPercent")
    @Expose
    private Integer recommendedPercent;
    @SerializedName("ScoreText")
    @Expose
    private String scoreText;
    @SerializedName("AverageReviewScores")
    @Expose
    private List<Object> averageReviewScores = null;
    @SerializedName("Reviews")
    @Expose
    private List<Object> reviews = null;
    @SerializedName("ReviewFilterItems")
    @Expose
    private List<ReviewFilterItem> reviewFilterItems = null;

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public Integer getRecommendedPercent() {
        return recommendedPercent;
    }

    public void setRecommendedPercent(Integer recommendedPercent) {
        this.recommendedPercent = recommendedPercent;
    }

    public String getScoreText() {
        return scoreText;
    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }

    public List<Object> getAverageReviewScores() {
        return averageReviewScores;
    }

    public void setAverageReviewScores(List<Object> averageReviewScores) {
        this.averageReviewScores = averageReviewScores;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public void setReviews(List<Object> reviews) {
        this.reviews = reviews;
    }

    public List<ReviewFilterItem> getReviewFilterItems() {
        return reviewFilterItems;
    }

    public void setReviewFilterItems(List<ReviewFilterItem> reviewFilterItems) {
        this.reviewFilterItems = reviewFilterItems;
    }

}
