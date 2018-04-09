
package com.eligasht.service.model.insurance.response.ResponsePreFactorDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreFactorService {

    @SerializedName("AdultsCount")
    @Expose
    private Integer adultsCount;
    @SerializedName("ChildsCount")
    @Expose
    private Integer childsCount;
    @SerializedName("CityEn")
    @Expose
    private String cityEn;
    @SerializedName("CityFa")
    @Expose
    private String cityFa;
    @SerializedName("Cost")
    @Expose
    private Integer cost;
    @SerializedName("CountryEn")
    @Expose
    private String countryEn;
    @SerializedName("CountryFa")
    @Expose
    private String countryFa;
    @SerializedName("CurrencyCode")
    @Expose
    private Object currencyCode;
    @SerializedName("InfantsCount")
    @Expose
    private Integer infantsCount;
    @SerializedName("PassengersList")
    @Expose
    private Object passengersList;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("ServiceDescEn")
    @Expose
    private String serviceDescEn;
    @SerializedName("ServiceDescFa")
    @Expose
    private String serviceDescFa;
    @SerializedName("ServiceID")
    @Expose
    private Integer serviceID;
    @SerializedName("ServiceNameEn")
    @Expose
    private String serviceNameEn;
    @SerializedName("ServiceNameFa")
    @Expose
    private String serviceNameFa;
    @SerializedName("ServicePrice")
    @Expose
    private Integer servicePrice;
    @SerializedName("ServiceType")
    @Expose
    private String serviceType;

    public Integer getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(Integer adultsCount) {
        this.adultsCount = adultsCount;
    }

    public Integer getChildsCount() {
        return childsCount;
    }

    public void setChildsCount(Integer childsCount) {
        this.childsCount = childsCount;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCountryFa() {
        return countryFa;
    }

    public void setCountryFa(String countryFa) {
        this.countryFa = countryFa;
    }

    public Object getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getInfantsCount() {
        return infantsCount;
    }

    public void setInfantsCount(Integer infantsCount) {
        this.infantsCount = infantsCount;
    }

    public Object getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(Object passengersList) {
        this.passengersList = passengersList;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getServiceDescEn() {
        return serviceDescEn;
    }

    public void setServiceDescEn(String serviceDescEn) {
        this.serviceDescEn = serviceDescEn;
    }

    public String getServiceDescFa() {
        return serviceDescFa;
    }

    public void setServiceDescFa(String serviceDescFa) {
        this.serviceDescFa = serviceDescFa;
    }

    public Integer getServiceID() {
        return serviceID;
    }

    public void setServiceID(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceNameEn() {
        return serviceNameEn;
    }

    public void setServiceNameEn(String serviceNameEn) {
        this.serviceNameEn = serviceNameEn;
    }

    public String getServiceNameFa() {
        return serviceNameFa;
    }

    public void setServiceNameFa(String serviceNameFa) {
        this.serviceNameFa = serviceNameFa;
    }

    public Integer getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Integer servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

}
