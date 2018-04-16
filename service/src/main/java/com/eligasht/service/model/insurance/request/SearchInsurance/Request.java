
package com.eligasht.service.model.insurance.request.SearchInsurance;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("AccomodationDays")
    @Expose
    private Integer accomodationDays;
    @SerializedName("BirthDateList")
    @Expose
    private List<BirthDateList> birthDateList = null;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("DepartureDate")
    @Expose
    private String departureDate;
    @SerializedName("identity")
    @Expose
    private Identity identity;
    @SerializedName("PassCount")
    @Expose
    private Integer passCount;
    @SerializedName("ReturnDate")
    @Expose
    private String returnDate;

    public Integer getAccomodationDays() {
        return accomodationDays;
    }

    public void setAccomodationDays(Integer accomodationDays) {
        this.accomodationDays = accomodationDays;
    }

    public List<BirthDateList> getBirthDateList() {
        return birthDateList;
    }

    public void setBirthDateList(List<BirthDateList> birthDateList) {
        this.birthDateList = birthDateList;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

}
