
package com.eligasht.service.model.newModel.startup.response;

import java.util.List;

import com.eligasht.service.model.error.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseStartup {

    @SerializedName("IsUpdataMandatory")
    @Expose
    private Boolean isUpdataMandatory;
    @SerializedName("HasUpdate")
    @Expose
    private Boolean hasUpdate;
    @SerializedName("Token")
    @Expose
    private Object token;
    @SerializedName("Branches")
    @Expose
    private List<Branch> branches = null;
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
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    @SerializedName("UpdateUrl")
    @Expose
    private List<String> UpdateUrl = null;

    public List<String> getUpdateUrl() {
        return UpdateUrl;
    }

    public void setUpdateUrl(List<String> updateUrl) {
        UpdateUrl = updateUrl;
    }

    public Boolean getUpdataMandatory() {
        return isUpdataMandatory;
    }

    public void setUpdataMandatory(Boolean updataMandatory) {
        isUpdataMandatory = updataMandatory;
    }


    public Boolean getIsUpdataMandatory() {
        return isUpdataMandatory;
    }

    public void setIsUpdataMandatory(Boolean isUpdataMandatory) {
        this.isUpdataMandatory = isUpdataMandatory;
    }

    public Boolean getHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(Boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
