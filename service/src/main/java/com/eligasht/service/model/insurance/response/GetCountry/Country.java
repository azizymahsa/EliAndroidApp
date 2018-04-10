
package com.eligasht.service.model.insurance.response.GetCountry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("CountryIsDefault")
    @Expose
    private Boolean countryIsDefault;
    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("CountryNameEn")
    @Expose
    private String countryNameEn;
    @SerializedName("CountryNameFa")
    @Expose
    private String countryNameFa;
    @SerializedName("HasInsurance")
    @Expose
    private Boolean hasInsurance;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Boolean getCountryIsDefault() {
        return countryIsDefault;
    }

    public void setCountryIsDefault(Boolean countryIsDefault) {
        this.countryIsDefault = countryIsDefault;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameFa() {
        return countryNameFa;
    }

    public void setCountryNameFa(String countryNameFa) {
        this.countryNameFa = countryNameFa;
    }

    public Boolean getHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

}
