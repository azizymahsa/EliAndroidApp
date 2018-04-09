
package com.eligasht.service.model.insurance.request.PurchaseInsurance;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("PassList")
    @Expose
    private List<PassList> passList = null;
    @SerializedName("PartnerList")
    @Expose
    private PartnerList partnerList;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("DepartureDate")
    @Expose
    private String departureDate;
    @SerializedName("DtStart")
    @Expose
    private String dtStart;
    @SerializedName("PlanCode")
    @Expose
    private String planCode;
    @SerializedName("ReturnDate")
    @Expose
    private String returnDate;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public List<PassList> getPassList() {
        return passList;
    }

    public void setPassList(List<PassList> passList) {
        this.passList = passList;
    }

    public PartnerList getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(PartnerList partnerList) {
        this.partnerList = partnerList;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDtStart() {
        return dtStart;
    }

    public void setDtStart(String dtStart) {
        this.dtStart = dtStart;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
