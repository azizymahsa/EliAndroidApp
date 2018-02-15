
package com.eligasht.reservation.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InsurancePlan_ {

    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("Code")
    @Expose
    private Integer code;
    @SerializedName("CoverInfos")
    @Expose
    private ArrayList<CoverInfo> coverInfos = null;
    @SerializedName("CoverLimit")
    @Expose
    private String coverLimit;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("DiscountPercentage")
    @Expose
    private Integer discountPercentage;
    @SerializedName("PassengerKey")
    @Expose
    private String passengerKey;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("TitleEnglish")
    @Expose
    private String titleEnglish;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ArrayList<CoverInfo> getCoverInfos() {
        return coverInfos;
    }

    public void setCoverInfos(ArrayList<CoverInfo> coverInfos) {
        this.coverInfos = coverInfos;
    }

    public String getCoverLimit() {
        return coverLimit;
    }

    public void setCoverLimit(String coverLimit) {
        this.coverLimit = coverLimit;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getPassengerKey() {
        return passengerKey;
    }

    public void setPassengerKey(String passengerKey) {
        this.passengerKey = passengerKey;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

}
