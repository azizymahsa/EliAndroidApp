
package com.eligasht.service.model.newModel.flight.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("ServiceID")
    @Expose
    private String serviceID;
    @SerializedName("ServiceTypeID")
    @Expose
    private Integer serviceTypeID;
    @SerializedName("CityEn")
    @Expose
    private String cityEn;
    @SerializedName("CityFa")
    @Expose
    private String cityFa;
    @SerializedName("ServiceNameFa")
    @Expose
    private String serviceNameFa;
    @SerializedName("ServiceNameEn")
    @Expose
    private String serviceNameEn;
    @SerializedName("ServiceDescFa")
    @Expose
    private String serviceDescFa;
    @SerializedName("ServiceDescEn")
    @Expose
    private String serviceDescEn;
    @SerializedName("ServiceAdlPrice")
    @Expose
    private Integer serviceAdlPrice;
    @SerializedName("ServiceChdPrice")
    @Expose
    private Integer serviceChdPrice;
    @SerializedName("ServiceInfPrice")
    @Expose
    private Integer serviceInfPrice;
    @SerializedName("Currency_ID")
    @Expose
    private Integer currencyID;
    @SerializedName("ServiceImgURL")
    @Expose
    private String serviceImgURL;
    @SerializedName("ServiceTypeEn")
    @Expose
    private String serviceTypeEn;
    @SerializedName("ServiceTypeFa")
    @Expose
    private String serviceTypeFa;
    @SerializedName("ServiceTypeImgURL")
    @Expose
    private String serviceTypeImgURL;
    @SerializedName("LoadDB")
    @Expose
    private Boolean loadDB;
    @SerializedName("AdditionalParam")
    @Expose
    private String additionalParam;
    @SerializedName("LstPassenger")
    @Expose
    private Object lstPassenger;
    @SerializedName("HasHotel")
    @Expose
    private Boolean hasHotel;
    @SerializedName("HasFlight")
    @Expose
    private Boolean hasFlight;
    @SerializedName("ServiceTotalPrice")
    @Expose
    private Integer serviceTotalPrice;
    @SerializedName("SelectID")
    @Expose
    private String selectID;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public Integer getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(Integer serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
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

    public Integer getServiceAdlPrice() {
        return serviceAdlPrice;
    }

    public void setServiceAdlPrice(Integer serviceAdlPrice) {
        this.serviceAdlPrice = serviceAdlPrice;
    }

    public Integer getServiceChdPrice() {
        return serviceChdPrice;
    }

    public void setServiceChdPrice(Integer serviceChdPrice) {
        this.serviceChdPrice = serviceChdPrice;
    }

    public Integer getServiceInfPrice() {
        return serviceInfPrice;
    }

    public void setServiceInfPrice(Integer serviceInfPrice) {
        this.serviceInfPrice = serviceInfPrice;
    }

    public Integer getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Integer currencyID) {
        this.currencyID = currencyID;
    }

    public String getServiceImgURL() {
        return serviceImgURL;
    }

    public void setServiceImgURL(String serviceImgURL) {
        this.serviceImgURL = serviceImgURL;
    }

    public String getServiceTypeEn() {
        return serviceTypeEn;
    }

    public void setServiceTypeEn(String serviceTypeEn) {
        this.serviceTypeEn = serviceTypeEn;
    }

    public String getServiceTypeFa() {
        return serviceTypeFa;
    }

    public void setServiceTypeFa(String serviceTypeFa) {
        this.serviceTypeFa = serviceTypeFa;
    }

    public String getServiceTypeImgURL() {
        return serviceTypeImgURL;
    }

    public void setServiceTypeImgURL(String serviceTypeImgURL) {
        this.serviceTypeImgURL = serviceTypeImgURL;
    }

    public Boolean getLoadDB() {
        return loadDB;
    }

    public void setLoadDB(Boolean loadDB) {
        this.loadDB = loadDB;
    }

    public String getAdditionalParam() {
        return additionalParam;
    }

    public void setAdditionalParam(String additionalParam) {
        this.additionalParam = additionalParam;
    }

    public Object getLstPassenger() {
        return lstPassenger;
    }

    public void setLstPassenger(Object lstPassenger) {
        this.lstPassenger = lstPassenger;
    }

    public Boolean getHasHotel() {
        return hasHotel;
    }

    public void setHasHotel(Boolean hasHotel) {
        this.hasHotel = hasHotel;
    }

    public Boolean getHasFlight() {
        return hasFlight;
    }

    public void setHasFlight(Boolean hasFlight) {
        this.hasFlight = hasFlight;
    }

    public Integer getServiceTotalPrice() {
        return serviceTotalPrice;
    }

    public void setServiceTotalPrice(Integer serviceTotalPrice) {
        this.serviceTotalPrice = serviceTotalPrice;
    }

    public String getSelectID() {
        return selectID;
    }

    public void setSelectID(String selectID) {
        this.selectID = selectID;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
