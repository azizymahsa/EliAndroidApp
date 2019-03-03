
package com.eligasht.service.model.newModel.hotel.review.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewComment {

    @SerializedName("ReviewCommentID")
    @Expose
    private Integer reviewCommentID;
    @SerializedName("ReviewID")
    @Expose
    private Integer reviewID;
    @SerializedName("SubmitName")
    @Expose
    private String submitName;
    @SerializedName("SubmitEmail")
    @Expose
    private String submitEmail;
    @SerializedName("SubmitImgURL")
    @Expose
    private String submitImgURL;
    @SerializedName("SubmitNickName")
    @Expose
    private String submitNickName;
    @SerializedName("SubmitDate")
    @Expose
    private String submitDate;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("WebUserID")
    @Expose
    private Integer webUserID;
    @SerializedName("AgcUserID")
    @Expose
    private Integer agcUserID;

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

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
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

    public String getSubmitNickName() {
        return submitNickName;
    }

    public void setSubmitNickName(String submitNickName) {
        this.submitNickName = submitNickName;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(Integer webUserID) {
        this.webUserID = webUserID;
    }

    public Integer getAgcUserID() {
        return agcUserID;
    }

    public void setAgcUserID(Integer agcUserID) {
        this.agcUserID = agcUserID;
    }

}
