
package com.eligasht.service.model.newModel.hotel.hotelDetail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewFilterItem {

    @SerializedName("ReviewCategoryItemsTitle")
    @Expose
    private String reviewCategoryItemsTitle;
    @SerializedName("ReviewCategoryItemsID")
    @Expose
    private Integer reviewCategoryItemsID;
    @SerializedName("ReviewCategoryID")
    @Expose
    private Integer reviewCategoryID;
    @SerializedName("ReviewCategoryTitle")
    @Expose
    private String reviewCategoryTitle;
    @SerializedName("ReviewsCount")
    @Expose
    private Integer reviewsCount;

    public String getReviewCategoryItemsTitle() {
        return reviewCategoryItemsTitle;
    }

    public void setReviewCategoryItemsTitle(String reviewCategoryItemsTitle) {
        this.reviewCategoryItemsTitle = reviewCategoryItemsTitle;
    }

    public Integer getReviewCategoryItemsID() {
        return reviewCategoryItemsID;
    }

    public void setReviewCategoryItemsID(Integer reviewCategoryItemsID) {
        this.reviewCategoryItemsID = reviewCategoryItemsID;
    }

    public Integer getReviewCategoryID() {
        return reviewCategoryID;
    }

    public void setReviewCategoryID(Integer reviewCategoryID) {
        this.reviewCategoryID = reviewCategoryID;
    }

    public String getReviewCategoryTitle() {
        return reviewCategoryTitle;
    }

    public void setReviewCategoryTitle(String reviewCategoryTitle) {
        this.reviewCategoryTitle = reviewCategoryTitle;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

}
