
package com.eligasht.service.model.hotel.detail.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelReviews {

    @SerializedName("AverageReviewScores")
    @Expose
    private List<AverageReviewScore> averageReviewScores = null;
    @SerializedName("AverageScore")
    @Expose
    private Double averageScore;
    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("RecommendedPercent")
    @Expose
    private Integer recommendedPercent;
    @SerializedName("ReviewFilterItems")
    @Expose
    private List<ReviewFilterItem> reviewFilterItems = null;
    @SerializedName("Reviews")
    @Expose
    private List<Review> reviews = null;
    @SerializedName("ReviewsCount")
    @Expose
    private Integer reviewsCount;
    @SerializedName("ScoreText")
    @Expose
    private String scoreText;

    public List<AverageReviewScore> getAverageReviewScores() {
        return averageReviewScores;
    }

    public void setAverageReviewScores(List<AverageReviewScore> averageReviewScores) {
        this.averageReviewScores = averageReviewScores;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

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

    public Integer getRecommendedPercent() {
        return recommendedPercent;
    }

    public void setRecommendedPercent(Integer recommendedPercent) {
        this.recommendedPercent = recommendedPercent;
    }

    public List<ReviewFilterItem> getReviewFilterItems() {
        return reviewFilterItems;
    }

    public void setReviewFilterItems(List<ReviewFilterItem> reviewFilterItems) {
        this.reviewFilterItems = reviewFilterItems;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getScoreText() {
        return scoreText;
    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }

}
