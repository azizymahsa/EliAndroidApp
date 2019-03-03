
package com.eligasht.service.model.newModel.login.loginUser.response;

import java.util.List;

import com.eligasht.service.model.newModel.hotel.search.response.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLoginUser {

    @SerializedName("WebUserProperties")
    @Expose
    private WebUserProperties webUserProperties;
    @SerializedName("LoginStatus")
    @Expose
    private String loginStatus;
    @SerializedName("PreviousContracts")
    @Expose
    private List<Object> previousContracts = null;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
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

    public WebUserProperties getWebUserProperties() {
        return webUserProperties;
    }

    public void setWebUserProperties(WebUserProperties webUserProperties) {
        this.webUserProperties = webUserProperties;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public List<Object> getPreviousContracts() {
        return previousContracts;
    }

    public void setPreviousContracts(List<Object> previousContracts) {
        this.previousContracts = previousContracts;
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
