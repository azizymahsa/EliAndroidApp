
package com.eligasht.service.model.newModel.flight.prefactor.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("ServiceID")
    @Expose
    private Integer serviceID;
    @SerializedName("ServiceType")
    @Expose
    private String serviceType;
    @SerializedName("ServiceNameFa")
    @Expose
    private String serviceNameFa;
    @SerializedName("ServiceNameEn")
    @Expose
    private String serviceNameEn;
    @SerializedName("CityFa")
    @Expose
    private String cityFa;
    @SerializedName("CityEn")
    @Expose
    private String cityEn;
    @SerializedName("CountryFa")
    @Expose
    private String countryFa;
    @SerializedName("CountryEn")
    @Expose
    private String countryEn;
    @SerializedName("ServiceDescFa")
    @Expose
    private String serviceDescFa;
    @SerializedName("ServiceDescEn")
    @Expose
    private String serviceDescEn;
    @SerializedName("ServicePrice")
    @Expose
    private ServicePrice servicePrice;
    @SerializedName("AdultsCount")
    @Expose
    private Integer adultsCount;
    @SerializedName("ChildsCount")
    @Expose
    private Integer childsCount;
    @SerializedName("InfantsCount")
    @Expose
    private Integer infantsCount;
    @SerializedName("Cost")
    @Expose
    private Integer cost;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("CurrencyCode")
    @Expose
    private Object currencyCode;
    @SerializedName("PassengersList")
    @Expose
    private Object passengersList;

    public Integer getServiceID() {
        return serviceID;
    }

    public void setServiceID(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceNameFa() {
        return serviceNameFa;
    }

    public void setServiceNameFa(String serviceNameFa) {
        this.serviceNameFa = serviceNameFa;
    }

    public String getServiceNameEn() {
        return serviceNameEn;
    }

    public void setServiceNameEn(String serviceNameEn) {
        this.serviceNameEn = serviceNameEn;
    }

    public String getCityFa() {
        return cityFa;
    }

    public void setCityFa(String cityFa) {
        this.cityFa = cityFa;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountryFa() {
        return countryFa;
    }

    public void setCountryFa(String countryFa) {
        this.countryFa = countryFa;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getServiceDescFa() {
        return serviceDescFa;
    }

    public void setServiceDescFa(String serviceDescFa) {
        this.serviceDescFa = serviceDescFa;
    }

    public String getServiceDescEn() {
        return serviceDescEn;
    }

    public void setServiceDescEn(String serviceDescEn) {
        this.serviceDescEn = serviceDescEn;
    }

    public ServicePrice getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(ServicePrice servicePrice) {
        this.servicePrice = servicePrice;
    }

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

    public Integer getInfantsCount() {
        return infantsCount;
    }

    public void setInfantsCount(Integer infantsCount) {
        this.infantsCount = infantsCount;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Object getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Object getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(Object passengersList) {
        this.passengersList = passengersList;
    }

}
