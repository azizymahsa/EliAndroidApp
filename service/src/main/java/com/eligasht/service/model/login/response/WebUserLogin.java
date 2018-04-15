
package com.eligasht.service.model.login.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebUserLogin {

    @SerializedName("LoginStatus")
    @Expose
    private String loginStatus;
    @SerializedName("PreviousContracts")
    @Expose
    private List<PreviousContract> previousContracts = null;
    @SerializedName("WebUserProperties")
    @Expose
    private WebUserProperties webUserProperties;

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public List<PreviousContract> getPreviousContracts() {
        return previousContracts;
    }

    public void setPreviousContracts(List<PreviousContract> previousContracts) {
        this.previousContracts = previousContracts;
    }

    public WebUserProperties getWebUserProperties() {
        return webUserProperties;
    }

    public void setWebUserProperties(WebUserProperties webUserProperties) {
        this.webUserProperties = webUserProperties;
    }

}
