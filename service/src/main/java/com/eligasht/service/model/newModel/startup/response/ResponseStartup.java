
package com.eligasht.service.model.newModel.startup.response;

import java.util.List;

import com.eligasht.service.model.newModel.hotel.search.response.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseStartup {

    @SerializedName("Branches")
    @Expose
    private List<Branch> branches = null;
    @SerializedName("IsUpdataMandatory")
    @Expose
    private Boolean isUpdataMandatory;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("LatestVersion")
    @Expose
    private String latestVersion;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Boolean getIsUpdataMandatory() {
        return isUpdataMandatory;
    }

    public void setIsUpdataMandatory(Boolean isUpdataMandatory) {
        this.isUpdataMandatory = isUpdataMandatory;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
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

}
