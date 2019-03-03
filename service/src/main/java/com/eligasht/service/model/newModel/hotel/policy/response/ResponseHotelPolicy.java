
package com.eligasht.service.model.newModel.hotel.policy.response;

import java.util.List;

import com.eligasht.service.model.newModel.insurance.response.purchase.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHotelPolicy {

    @SerializedName("Key")
    @Expose
    private String key;
    @SerializedName("CancellationPolicies")
    @Expose
    private List<CancellationPolicy> cancellationPolicies = null;
    @SerializedName("Errors")
    @Expose
    private Error errors;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<CancellationPolicy> getCancellationPolicies() {
        return cancellationPolicies;
    }

    public void setCancellationPolicies(List<CancellationPolicy> cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
    }

    public Error getErrors() {
        return errors;
    }

    public void setErrors(Error errors) {
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
