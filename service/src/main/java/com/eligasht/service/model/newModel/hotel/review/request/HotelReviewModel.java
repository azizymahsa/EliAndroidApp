
package com.eligasht.service.model.newModel.hotel.review.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelReviewModel {

    @SerializedName("AverageScore")
    @Expose
    private String averageScore;
    @SerializedName("HotelID")
    @Expose
    private String hotelID;
    @SerializedName("Reviews")
    @Expose
    private List<Review> reviews = null;
    @SerializedName("RecommendedPercent")
    @Expose
    private String recommendedPercent;

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getRecommendedPercent() {
        return recommendedPercent;
    }

    public void setRecommendedPercent(String recommendedPercent) {
        this.recommendedPercent = recommendedPercent;
    }

}
