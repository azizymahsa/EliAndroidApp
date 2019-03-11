
package com.eligasht.service.model.newModel.promotion.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePromotionCode {

    @SerializedName("PromoCodeID")
    @Expose
    private Integer promoCodeID;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Validity")
    @Expose
    private String validity;
    @SerializedName("ValidityText")
    @Expose
    private String validityText;
    @SerializedName("DisounctAmount")
    @Expose
    private Integer disounctAmount;
    @SerializedName("PaymentAfterDiscountAmount")
    @Expose
    private Integer paymentAfterDiscountAmount;
    @SerializedName("PromoCode_DiscountPPP")
    @Expose
    private Integer promoCodeDiscountPPP;
    @SerializedName("PromoCode_DiscountPPC")
    @Expose
    private Integer promoCodeDiscountPPC;
    @SerializedName("PromoCode_DiscountIsPC")
    @Expose
    private Boolean promoCodeDiscountIsPC;

    public Integer getPromoCodeID() {
        return promoCodeID;
    }

    public void setPromoCodeID(Integer promoCodeID) {
        this.promoCodeID = promoCodeID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getValidityText() {
        return validityText;
    }

    public void setValidityText(String validityText) {
        this.validityText = validityText;
    }

    public Integer getDisounctAmount() {
        return disounctAmount;
    }

    public void setDisounctAmount(Integer disounctAmount) {
        this.disounctAmount = disounctAmount;
    }

    public Integer getPaymentAfterDiscountAmount() {
        return paymentAfterDiscountAmount;
    }

    public void setPaymentAfterDiscountAmount(Integer paymentAfterDiscountAmount) {
        this.paymentAfterDiscountAmount = paymentAfterDiscountAmount;
    }

    public Integer getPromoCodeDiscountPPP() {
        return promoCodeDiscountPPP;
    }

    public void setPromoCodeDiscountPPP(Integer promoCodeDiscountPPP) {
        this.promoCodeDiscountPPP = promoCodeDiscountPPP;
    }

    public Integer getPromoCodeDiscountPPC() {
        return promoCodeDiscountPPC;
    }

    public void setPromoCodeDiscountPPC(Integer promoCodeDiscountPPC) {
        this.promoCodeDiscountPPC = promoCodeDiscountPPC;
    }

    public Boolean getPromoCodeDiscountIsPC() {
        return promoCodeDiscountIsPC;
    }

    public void setPromoCodeDiscountIsPC(Boolean promoCodeDiscountIsPC) {
        this.promoCodeDiscountIsPC = promoCodeDiscountIsPC;
    }

}
