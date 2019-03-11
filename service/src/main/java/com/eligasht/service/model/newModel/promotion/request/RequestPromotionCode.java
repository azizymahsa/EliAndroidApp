
package com.eligasht.service.model.newModel.promotion.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPromotionCode {

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
    private String captchaText;
    @SerializedName("CaptchaCName")
    @Expose
    private String captchaCName;

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

    public String getCaptchaText() {
        return captchaText;
    }

    public void setCaptchaText(String captchaText) {
        this.captchaText = captchaText;
    }

    public String getCaptchaCName() {
        return captchaCName;
    }

    public void setCaptchaCName(String captchaCName) {
        this.captchaCName = captchaCName;
    }

}
