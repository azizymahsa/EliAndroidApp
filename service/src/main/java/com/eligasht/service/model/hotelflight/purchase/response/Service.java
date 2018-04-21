
package com.eligasht.service.model.hotelflight.purchase.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("AdditionalParam")
    @Expose
    private Object additionalParam;
    @SerializedName("CityEn")
    @Expose
    private String cityEn;
    @SerializedName("CityFa")
    @Expose
    private String cityFa;
    @SerializedName("Currency_ID")
    @Expose
    private Integer currencyID;
    @SerializedName("ExcursionDta")
    @Expose
    private ExcursionDta excursionDta;
    @SerializedName("HasFlight")
    @Expose
    private Boolean hasFlight;
    @SerializedName("HasHotel")
    @Expose
    private Boolean hasHotel;
    @SerializedName("LoadDB")
    @Expose
    private Boolean loadDB;
    @SerializedName("LstPassenger")
    @Expose
    private List<LstPassenger> lstPassenger = null;
    @SerializedName("SelectID")
    @Expose
    private String selectID;
    @SerializedName("ServiceAdlPrice")
    @Expose
    private Integer serviceAdlPrice;
    @SerializedName("ServiceChdPrice")
    @Expose
    private Integer serviceChdPrice;
    @SerializedName("ServiceDescEn")
    @Expose
    private String serviceDescEn;
    @SerializedName("ServiceDescFa")
    @Expose
    private String serviceDescFa;
    @SerializedName("ServiceID")
    @Expose
    private String serviceID;
    @SerializedName("ServiceImgURL")
    @Expose
    private String serviceImgURL;
    @SerializedName("ServiceInfPrice")
    @Expose
    private Integer serviceInfPrice;
    @SerializedName("ServiceNameEn")
    @Expose
    private String serviceNameEn;
    @SerializedName("ServiceNameFa")
    @Expose
    private String serviceNameFa;
    @SerializedName("ServiceTotalPrice")
    @Expose
    private String serviceTotalPrice;
    @SerializedName("ServiceTypeEn")
    @Expose
    private String serviceTypeEn;
    @SerializedName("ServiceTypeFa")
    @Expose
    private String serviceTypeFa;
    @SerializedName("ServiceTypeID")
    @Expose
    private Integer serviceTypeID;
    @SerializedName("ServiceTypeImgURL")
    @Expose
    private String serviceTypeImgURL;

    public Object getAdditionalParam() {
        return additionalParam;
    }

    public void setAdditionalParam(Object additionalParam) {
        this.additionalParam = additionalParam;
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

    public ExcursionDta getExcursionDta() {
        return excursionDta;
    }

    public void setExcursionDta(ExcursionDta excursionDta) {
        this.excursionDta = excursionDta;
    }

    public Boolean getHasFlight() {
        return hasFlight;
    }

    public void setHasFlight(Boolean hasFlight) {
        this.hasFlight = hasFlight;
    }

    public Boolean getHasHotel() {
        return hasHotel;
    }

    public void setHasHotel(Boolean hasHotel) {
        this.hasHotel = hasHotel;
    }

    public Boolean getLoadDB() {
        return loadDB;
    }

    public void setLoadDB(Boolean loadDB) {
        this.loadDB = loadDB;
    }

    public List<LstPassenger> getLstPassenger() {
        return lstPassenger;
    }

    public void setLstPassenger(List<LstPassenger> lstPassenger) {
        this.lstPassenger = lstPassenger;
    }

    public String getSelectID() {
        return selectID;
    }

    public void setSelectID(String selectID) {
        this.selectID = selectID;
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

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceImgURL() {
        return serviceImgURL;
    }

    public void setServiceImgURL(String serviceImgURL) {
        this.serviceImgURL = serviceImgURL;
    }

    public Integer getServiceInfPrice() {
        return serviceInfPrice;
    }

    public void setServiceInfPrice(Integer serviceInfPrice) {
        this.serviceInfPrice = serviceInfPrice;
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

    public String getServiceTotalPrice() {
        return serviceTotalPrice;
    }

    public void setServiceTotalPrice(String serviceTotalPrice) {
        this.serviceTotalPrice = serviceTotalPrice;
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

    public Integer getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(Integer serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getServiceTypeImgURL() {
        return serviceTypeImgURL;
    }

    public void setServiceTypeImgURL(String serviceTypeImgURL) {
        this.serviceTypeImgURL = serviceTypeImgURL;
    }

}
