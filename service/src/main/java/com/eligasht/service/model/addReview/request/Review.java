
package com.eligasht.service.model.addReview.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("AgcUserID")
    @Expose
    private Integer agcUserID;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("IsRecommended")
    @Expose
    private String isRecommended;
    @SerializedName("ReviewCommentID")
    @Expose
    private Integer reviewCommentID;
    @SerializedName("ReviewID")
    @Expose
    private Integer reviewID;
    @SerializedName("ReviewScores")
    @Expose
    private List<ReviewScore> reviewScores = null;
    @SerializedName("SubmitEmail")
    @Expose
    private String submitEmail;
    @SerializedName("SubmitName")
    @Expose
    private String submitName;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("WebUserID")
    @Expose
    private String webUserID;

    public Integer getAgcUserID() {
        return agcUserID;
    }

    public void setAgcUserID(Integer agcUserID) {
        this.agcUserID = agcUserID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(String isRecommended) {
        this.isRecommended = isRecommended;
    }

    public Integer getReviewCommentID() {
        return reviewCommentID;
    }

    public void setReviewCommentID(Integer reviewCommentID) {
        this.reviewCommentID = reviewCommentID;
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

    public String getSubmitEmail() {
        return submitEmail;
    }

    public void setSubmitEmail(String submitEmail) {
        this.submitEmail = submitEmail;
    }

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(String webUserID) {
        this.webUserID = webUserID;
    }

}
