
package com.eligasht.service.model.newModel.insurance.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Insurance {

    @SerializedName("InsuranceID")
    @Expose
    private String insuranceID;
    @SerializedName("InsuranceDBID")
    @Expose
    private Object insuranceDBID;
    @SerializedName("InsuranceNameEn")
    @Expose
    private String insuranceNameEn;
    @SerializedName("InsuranceNameFa")
    @Expose
    private String insuranceNameFa;
    @SerializedName("InsuranceTypeEn")
    @Expose
    private Object insuranceTypeEn;
    @SerializedName("InsuranceTypeFa")
    @Expose
    private Object insuranceTypeFa;
    @SerializedName("InsuranceDescEn")
    @Expose
    private Object insuranceDescEn;
    @SerializedName("InsuranceDescFa")
    @Expose
    private Object insuranceDescFa;
    @SerializedName("CityEn")
    @Expose
    private String cityEn;
    @SerializedName("CityFa")
    @Expose
    private String cityFa;
    @SerializedName("Currency_ID")
    @Expose
    private Integer currencyID;
    @SerializedName("InsuranceAdlPrice")
    @Expose
    private Integer insuranceAdlPrice;
    @SerializedName("InsuranceChdPrice")
    @Expose
    private Integer insuranceChdPrice;
    @SerializedName("InsuranceInfPrice")
    @Expose
    private Integer insuranceInfPrice;
    @SerializedName("InsuranceDuration")
    @Expose
    private Integer insuranceDuration;
    @SerializedName("InsuranceImgURL")
    @Expose
    private String insuranceImgURL;
    @SerializedName("InsuranceTypeImgURL")
    @Expose
    private Object insuranceTypeImgURL;
    @SerializedName("LoadDB")
    @Expose
    private Boolean loadDB;
    @SerializedName("TravelInsuranceCoverages")
    @Expose
    private List<TravelInsuranceCoverage> travelInsuranceCoverages = null;
    @SerializedName("TravelInsurancePricePP")
    @Expose
    private Object travelInsurancePricePP;
    @SerializedName("PassengerKey")
    @Expose
    private Integer passengerKey;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("PlanCode")
    @Expose
    private Integer planCode;
    @SerializedName("CoverLimit")
    @Expose
    private String coverLimit;
    @SerializedName("InsurancePrice")
    @Expose
    private Integer insurancePrice;
    @SerializedName("InsuranceDiscount")
    @Expose
    private Integer insuranceDiscount;
    @SerializedName("InsuranceDiscountPercentage")
    @Expose
    private Integer insuranceDiscountPercentage;

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public Object getInsuranceDBID() {
        return insuranceDBID;
    }

    public void setInsuranceDBID(Object insuranceDBID) {
        this.insuranceDBID = insuranceDBID;
    }

    public String getInsuranceNameEn() {
        return insuranceNameEn;
    }

    public void setInsuranceNameEn(String insuranceNameEn) {
        this.insuranceNameEn = insuranceNameEn;
    }

    public String getInsuranceNameFa() {
        return insuranceNameFa;
    }

    public void setInsuranceNameFa(String insuranceNameFa) {
        this.insuranceNameFa = insuranceNameFa;
    }

    public Object getInsuranceTypeEn() {
        return insuranceTypeEn;
    }

    public void setInsuranceTypeEn(Object insuranceTypeEn) {
        this.insuranceTypeEn = insuranceTypeEn;
    }

    public Object getInsuranceTypeFa() {
        return insuranceTypeFa;
    }

    public void setInsuranceTypeFa(Object insuranceTypeFa) {
        this.insuranceTypeFa = insuranceTypeFa;
    }

    public Object getInsuranceDescEn() {
        return insuranceDescEn;
    }

    public void setInsuranceDescEn(Object insuranceDescEn) {
        this.insuranceDescEn = insuranceDescEn;
    }

    public Object getInsuranceDescFa() {
        return insuranceDescFa;
    }

    public void setInsuranceDescFa(Object insuranceDescFa) {
        this.insuranceDescFa = insuranceDescFa;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityFa() {
        return cityFa;
    }

    public void setCityFa(String cityFa) {
        this.cityFa = cityFa;
    }

    public Integer getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Integer currencyID) {
        this.currencyID = currencyID;
    }

    public Integer getInsuranceAdlPrice() {
        return insuranceAdlPrice;
    }

    public void setInsuranceAdlPrice(Integer insuranceAdlPrice) {
        this.insuranceAdlPrice = insuranceAdlPrice;
    }

    public Integer getInsuranceChdPrice() {
        return insuranceChdPrice;
    }

    public void setInsuranceChdPrice(Integer insuranceChdPrice) {
        this.insuranceChdPrice = insuranceChdPrice;
    }

    public Integer getInsuranceInfPrice() {
        return insuranceInfPrice;
    }

    public void setInsuranceInfPrice(Integer insuranceInfPrice) {
        this.insuranceInfPrice = insuranceInfPrice;
    }

    public Integer getInsuranceDuration() {
        return insuranceDuration;
    }

    public void setInsuranceDuration(Integer insuranceDuration) {
        this.insuranceDuration = insuranceDuration;
    }

    public String getInsuranceImgURL() {
        return insuranceImgURL;
    }

    public void setInsuranceImgURL(String insuranceImgURL) {
        this.insuranceImgURL = insuranceImgURL;
    }

    public Object getInsuranceTypeImgURL() {
        return insuranceTypeImgURL;
    }

    public void setInsuranceTypeImgURL(Object insuranceTypeImgURL) {
        this.insuranceTypeImgURL = insuranceTypeImgURL;
    }

    public Boolean getLoadDB() {
        return loadDB;
    }

    public void setLoadDB(Boolean loadDB) {
        this.loadDB = loadDB;
    }

    public List<TravelInsuranceCoverage> getTravelInsuranceCoverages() {
        return travelInsuranceCoverages;
    }

    public void setTravelInsuranceCoverages(List<TravelInsuranceCoverage> travelInsuranceCoverages) {
        this.travelInsuranceCoverages = travelInsuranceCoverages;
    }

    public Object getTravelInsurancePricePP() {
        return travelInsurancePricePP;
    }

    public void setTravelInsurancePricePP(Object travelInsurancePricePP) {
        this.travelInsurancePricePP = travelInsurancePricePP;
    }

    public Integer getPassengerKey() {
        return passengerKey;
    }

    public void setPassengerKey(Integer passengerKey) {
        this.passengerKey = passengerKey;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPlanCode() {
        return planCode;
    }

    public void setPlanCode(Integer planCode) {
        this.planCode = planCode;
    }

    public String getCoverLimit() {
        return coverLimit;
    }

    public void setCoverLimit(String coverLimit) {
        this.coverLimit = coverLimit;
    }

    public Integer getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(Integer insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Integer getInsuranceDiscount() {
        return insuranceDiscount;
    }

    public void setInsuranceDiscount(Integer insuranceDiscount) {
        this.insuranceDiscount = insuranceDiscount;
    }

    public Integer getInsuranceDiscountPercentage() {
        return insuranceDiscountPercentage;
    }

    public void setInsuranceDiscountPercentage(Integer insuranceDiscountPercentage) {
        this.insuranceDiscountPercentage = insuranceDiscountPercentage;
    }

}
