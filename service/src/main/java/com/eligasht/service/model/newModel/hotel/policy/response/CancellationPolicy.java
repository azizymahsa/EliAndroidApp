
package com.eligasht.service.model.newModel.hotel.policy.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancellationPolicy {

    @SerializedName("ReturnAmount")
    @Expose
    private Integer returnAmount;
    @SerializedName("Currency")
    @Expose
    private Object currency;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("DescriptionEn")
    @Expose
    private String descriptionEn;
    @SerializedName("DescriptionFa")
    @Expose
    private Object descriptionFa;
    @SerializedName("RoomNo")
    @Expose
    private Object roomNo;
    @SerializedName("Errors")
    @Expose
    private Object errors;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public Integer getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Integer returnAmount) {
        this.returnAmount = returnAmount;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public Object getDescriptionFa() {
        return descriptionFa;
    }

    public void setDescriptionFa(Object descriptionFa) {
        this.descriptionFa = descriptionFa;
    }

    public Object getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Object roomNo) {
        this.roomNo = roomNo;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Object getWarningss() {
        return warningss;
    }

    public void setWarningss(Object warningss) {
        this.warningss = warningss;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
