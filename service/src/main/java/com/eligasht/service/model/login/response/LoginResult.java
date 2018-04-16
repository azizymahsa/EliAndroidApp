
package com.eligasht.service.model.login.response;

import com.eligasht.service.model.error.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class LoginResult {

    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("WebUserLogin")
    @Expose
    private WebUserLogin webUserLogin;

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public List<Error>  getErrors() {
        return errors;
    }

    public void setErrors(List<Error>  errors) {
        this.errors = errors;
    }

    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public Object getWarningss() {
        return warningss;
    }

    public void setWarningss(Object warningss) {
        this.warningss = warningss;
    }

    public WebUserLogin getWebUserLogin() {
        return webUserLogin;
    }

    public void setWebUserLogin(WebUserLogin webUserLogin) {
        this.webUserLogin = webUserLogin;
    }

}
