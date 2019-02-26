
package com.eligasht.service.model.newModel.hotel.preSearch.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHotelPreSearch {

    @SerializedName("SuccessResult")
    @Expose
    private Integer successResult;
    @SerializedName("ResultText")
    @Expose
    private String resultText;
    @SerializedName("QueryString")
    @Expose
    private Object queryString;
    @SerializedName("Errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("Warningss")
    @Expose
    private List<Object> warningss = null;
    @SerializedName("Comments")
    @Expose
    private Comments comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public Integer getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(Integer successResult) {
        this.successResult = successResult;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public Object getQueryString() {
        return queryString;
    }

    public void setQueryString(Object queryString) {
        this.queryString = queryString;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<Object> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Object> warningss) {
        this.warningss = warningss;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
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
