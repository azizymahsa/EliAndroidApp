
package com.eligasht.service.model.newModel.insurance.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseInsuranceSearchResult {

    @SerializedName("InsuranceSearchResult")
    @Expose
    private InsuranceSearchResult insuranceSearchResult;
    @SerializedName("PassengerCount")
    @Expose
    private Integer passengerCount;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("InsuranceDuration")
    @Expose
    private Integer insuranceDuration;
    @SerializedName("CountryNameFa")
    @Expose
    private String countryNameFa;
    @SerializedName("CountryNameEn")
    @Expose
    private String countryNameEn;

    public InsuranceSearchResult getInsuranceSearchResult() {
        return insuranceSearchResult;
    }

    public void setInsuranceSearchResult(InsuranceSearchResult insuranceSearchResult) {
        this.insuranceSearchResult = insuranceSearchResult;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Integer getInsuranceDuration() {
        return insuranceDuration;
    }

    public void setInsuranceDuration(Integer insuranceDuration) {
        this.insuranceDuration = insuranceDuration;
    }

    public String getCountryNameFa() {
        return countryNameFa;
    }

    public void setCountryNameFa(String countryNameFa) {
        this.countryNameFa = countryNameFa;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

}
