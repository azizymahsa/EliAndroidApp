
package com.eligasht.service.model.newModel.flight.prefactor.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscountDetail {

    @SerializedName("RegisterDiscountResult")
    @Expose
    private String registerDiscountResult;
    @SerializedName("PrefactorNum")
    @Expose
    private String prefactorNum;
    @SerializedName("DiscountCode")
    @Expose
    private String discountCode;
    @SerializedName("CaptchaText")
    @Expose
    private Object captchaText;
    @SerializedName("CaptchaCName")
    @Expose
    private Object captchaCName;

    public String getRegisterDiscountResult() {
        return registerDiscountResult;
    }

    public void setRegisterDiscountResult(String registerDiscountResult) {
        this.registerDiscountResult = registerDiscountResult;
    }

    public String getPrefactorNum() {
        return prefactorNum;
    }

    public void setPrefactorNum(String prefactorNum) {
        this.prefactorNum = prefactorNum;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Object getCaptchaText() {
        return captchaText;
    }

    public void setCaptchaText(Object captchaText) {
        this.captchaText = captchaText;
    }

    public Object getCaptchaCName() {
        return captchaCName;
    }

    public void setCaptchaCName(Object captchaCName) {
        this.captchaCName = captchaCName;
    }

}
