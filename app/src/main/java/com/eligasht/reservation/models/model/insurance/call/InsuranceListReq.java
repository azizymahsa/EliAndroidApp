
package com.eligasht.reservation.models.model.insurance.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.insurance.BirthDateList;

import java.util.ArrayList;

public class InsuranceListReq {

    @SerializedName("identity")
    @Expose
    private Identity identity;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("AccomodationDays")
    @Expose
    private Integer accomodationDays;
    @SerializedName("DepartureDate")
    @Expose
    private String departureDate;
    @SerializedName("ReturnDate")
    @Expose
    private String returnDate;
    @SerializedName("PassCount")
    @Expose
    private Integer passCount;
    @SerializedName("BirthDateList")
    @Expose
    private ArrayList<BirthDateList> birthDateList = null;

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getAccomodationDays() {
        return accomodationDays;
    }

    public void setAccomodationDays(Integer accomodationDays) {
        this.accomodationDays = accomodationDays;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public ArrayList<BirthDateList> getBirthDateList() {
        return birthDateList;
    }

    public void setBirthDateList(ArrayList<BirthDateList> birthDateList) {
        this.birthDateList = birthDateList;
    }

}
