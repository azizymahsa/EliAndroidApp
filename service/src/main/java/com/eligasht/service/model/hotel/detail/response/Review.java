
package com.eligasht.service.model.hotel.detail.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("AgcUserID")
    @Expose
    private Integer agcUserID;
    @SerializedName("AverageScore")
    @Expose
    private Integer averageScore;
    @SerializedName("CommentsCount")
    @Expose
    private Integer commentsCount;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("Device")
    @Expose
    private String device;
    @SerializedName("HelpfulAmount")
    @Expose
    private Integer helpfulAmount;
    @SerializedName("IsRecommended")
    @Expose
    private Boolean isRecommended;
    @SerializedName("PriceType")
    @Expose
    private String priceType;
    @SerializedName("PriceTypeID")
    @Expose
    private Integer priceTypeID;
    @SerializedName("ReviewComments")
    @Expose
    private Object reviewComments;
    @SerializedName("ReviewID")
    @Expose
    private Integer reviewID;
    @SerializedName("ReviewScores")
    @Expose
    private List<ReviewScore> reviewScores = null;
    @SerializedName("SubmitDate")
    @Expose
    private String submitDate;
    @SerializedName("SubmitEmail")
    @Expose
    private String submitEmail;
    @SerializedName("SubmitImgURL")
    @Expose
    private String submitImgURL;
    @SerializedName("SubmitLocation")
    @Expose
    private String submitLocation;
    @SerializedName("SubmitName")
    @Expose
    private String submitName;
    @SerializedName("SubmitNickName")
    @Expose
    private String submitNickName;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("TraveSeasonID")
    @Expose
    private Integer traveSeasonID;
    @SerializedName("TravelSeason")
    @Expose
    private String travelSeason;
    @SerializedName("TravelType")
    @Expose
    private String travelType;
    @SerializedName("TravelTypeID")
    @Expose
    private Integer travelTypeID;
    @SerializedName("WebUserID")
    @Expose
    private Integer webUserID;

    public Integer getAgcUserID() {
        return agcUserID;
    }

    public void setAgcUserID(Integer agcUserID) {
        this.agcUserID = agcUserID;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Integer getHelpfulAmount() {
        return helpfulAmount;
    }

    public void setHelpfulAmount(Integer helpfulAmount) {
        this.helpfulAmount = helpfulAmount;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Integer getPriceTypeID() {
        return priceTypeID;
    }

    public void setPriceTypeID(Integer priceTypeID) {
        this.priceTypeID = priceTypeID;
    }

    public Object getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(Object reviewComments) {
        this.reviewComments = reviewComments;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public List<ReviewScore> getReviewScores() {
        return reviewScores;
    }

    public void setReviewScores(List<ReviewScore> reviewScores) {
        this.reviewScores = reviewScores;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getSubmitEmail() {
        return submitEmail;
    }

    public void setSubmitEmail(String submitEmail) {
        this.submitEmail = submitEmail;
    }

    public String getSubmitImgURL() {
        return submitImgURL;
    }

    public void setSubmitImgURL(String submitImgURL) {
        this.submitImgURL = submitImgURL;
    }

    public String getSubmitLocation() {
        return submitLocation;
    }

    public void setSubmitLocation(String submitLocation) {
        this.submitLocation = submitLocation;
    }

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public String getSubmitNickName() {
        return submitNickName;
    }

    public void setSubmitNickName(String submitNickName) {
        this.submitNickName = submitNickName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTraveSeasonID() {
        return traveSeasonID;
    }

    public void setTraveSeasonID(Integer traveSeasonID) {
        this.traveSeasonID = traveSeasonID;
    }

    public String getTravelSeason() {
        return travelSeason;
    }

    public void setTravelSeason(String travelSeason) {
        this.travelSeason = travelSeason;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public Integer getTravelTypeID() {
        return travelTypeID;
    }

    public void setTravelTypeID(Integer travelTypeID) {
        this.travelTypeID = travelTypeID;
    }

    public Integer getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(Integer webUserID) {
        this.webUserID = webUserID;
    }

}
