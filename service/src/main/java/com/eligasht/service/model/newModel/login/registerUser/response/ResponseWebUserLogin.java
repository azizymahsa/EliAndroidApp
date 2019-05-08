package com.eligasht.service.model.newModel.login.registerUser.response;

import com.eligasht.service.model.newModel.hotel.search.response.Error;
import com.eligasht.service.model.newModel.login.loginUser.response.WebUserProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ResponseWebUserLogin {
    @SerializedName("WebUserProperties")
    @Expose
    private WebUserProperties WebUserProperties ;

    @SerializedName("LoginCode")
    @Expose
    private int LoginCode ;

    @SerializedName("LoginStatus")
    @Expose
    private String LoginStatus ;
    @SerializedName("LoginResult")
    @Expose
    private String LoginResult ;
    @SerializedName("LoginValidity")
    @Expose
    private String LoginValidity;
    @SerializedName("PreviousContracts")
    @Expose
    private List<Contract> PreviousContracts ;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("Comments")
    @Expose
    private Object comments;

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

    public com.eligasht.service.model.newModel.login.loginUser.response.WebUserProperties getWebUserProperties() {
        return WebUserProperties;
    }

    public void setWebUserProperties(WebUserProperties webUserProperties) {
        WebUserProperties = webUserProperties;
    }

    public int getLoginCode() {
        return LoginCode;
    }

    public void setLoginCode(int loginCode) {
        LoginCode = loginCode;
    }

    public String getLoginStatus() {
        return LoginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        LoginStatus = loginStatus;
    }

    public String getLoginResult() {
        return LoginResult;
    }

    public void setLoginResult(String loginResult) {
        LoginResult = loginResult;
    }

    public String getLoginValidity() {
        return LoginValidity;
    }

    public void setLoginValidity(String loginValidity) {
        LoginValidity = loginValidity;
    }

    public List<Contract> getPreviousContracts() {
        return PreviousContracts;
    }

    public void setPreviousContracts(List<Contract> previousContracts) {
        PreviousContracts = previousContracts;
    }
}
