
package com.eligasht.service.model.newModel.hotelFlight.loadFlight.response;

import java.util.List;

import com.eligasht.service.model.newModel.hotel.purchase.request.Warnings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLoadFlight {

    @SerializedName("HFlight")
    @Expose
    private HFlight hFlight;
    @SerializedName("QueryString")
    @Expose
    private Object queryString;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("Warningss")
    @Expose
    private List<Warnings> warningss = null;
    @SerializedName("Comments")
    @Expose
    private Comments comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Warnings> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Warnings> warningss) {
        this.warningss = warningss;
    }

    public HFlight getHFlight() {
        return hFlight;
    }

    public void setHFlight(HFlight hFlight) {
        this.hFlight = hFlight;
    }

    public Object getQueryString() {
        return queryString;
    }

    public void setQueryString(Object queryString) {
        this.queryString = queryString;
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
