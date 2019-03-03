
package com.eligasht.service.model.newModel.hotel.review.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("WebUserID")
    @Expose
    private Integer webUserID;
    @SerializedName("IsRecommended")
    @Expose
    private Boolean isRecommended;
    @SerializedName("SubmitName")
    @Expose
    private String submitName;
    @SerializedName("ReviewCommentID")
    @Expose
    private Integer reviewCommentID;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("ReviewID")
    @Expose
    private Integer reviewID;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("SubmitEmail")
    @Expose
    private String submitEmail;
    @SerializedName("AgcUserID")
    @Expose
    private Integer agcUserID;
    @SerializedName("ReviewScores")
    @Expose
    private List<ReviewScore> reviewScores = null;

    public Integer getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(Integer webUserID) {
        this.webUserID = webUserID;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public Integer getReviewCommentID() {
        return reviewCommentID;
    }

    public void setReviewCommentID(Integer reviewCommentID) {
        this.reviewCommentID = reviewCommentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubmitEmail() {
        return submitEmail;
    }

    public void setSubmitEmail(String submitEmail) {
        this.submitEmail = submitEmail;
    }

    public Integer getAgcUserID() {
        return agcUserID;
    }

    public void setAgcUserID(Integer agcUserID) {
        this.agcUserID = agcUserID;
    }

    public List<ReviewScore> getReviewScores() {
        return reviewScores;
    }

    public void setReviewScores(List<ReviewScore> reviewScores) {
        this.reviewScores = reviewScores;
    }

}
