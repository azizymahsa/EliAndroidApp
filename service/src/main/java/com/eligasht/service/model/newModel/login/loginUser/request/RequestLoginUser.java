
package com.eligasht.service.model.newModel.login.loginUser.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLoginUser {

    @SerializedName("UserMailOrMobile")
    @Expose
    private String userMailOrMobile;
    @SerializedName("UserMail")
    @Expose
    private String userMail;
    @SerializedName("UserMobile")
    @Expose
    private String userMobile;
    @SerializedName("UserPassword")
    @Expose
    private String userPassword;
    @SerializedName("RemmemberMe")
    @Expose
    private Boolean remmemberMe;
    @SerializedName("IsModal")
    @Expose
    private Boolean isModal;
    @SerializedName("ShowCaptcha")
    @Expose
    private Boolean showCaptcha;



    public String getUserMailOrMobile() {
        return userMailOrMobile;
    }

    public void setUserMailOrMobile(String userMailOrMobile) {
        this.userMailOrMobile = userMailOrMobile;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getRemmemberMe() {
        return remmemberMe;
    }

    public void setRemmemberMe(Boolean remmemberMe) {
        this.remmemberMe = remmemberMe;
    }

    public Boolean getIsModal() {
        return isModal;
    }

    public void setIsModal(Boolean isModal) {
        this.isModal = isModal;
    }

    public Boolean getShowCaptcha() {
        return showCaptcha;
    }

    public void setShowCaptcha(Boolean showCaptcha) {
        this.showCaptcha = showCaptcha;
    }

}
