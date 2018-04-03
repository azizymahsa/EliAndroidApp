
package com.eligasht.service.model.flight.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airport {

    @SerializedName("AirportCode")
    @Expose
    private String airportCode;
    @SerializedName("AirportID")
    @Expose
    private String airportID;
    @SerializedName("AirportName")
    @Expose
    private String airportName;
    @SerializedName("AirportNameEn")
    @Expose
    private Object airportNameEn;
    @SerializedName("AirportNameFa")
    @Expose
    private Object airportNameFa;
    @SerializedName("CityCode")
    @Expose
    private Object cityCode;
    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("CityNameEn")
    @Expose
    private Object cityNameEn;
    @SerializedName("CityNameFa")
    @Expose
    private Object cityNameFa;
    @SerializedName("CountryCode")
    @Expose
    private Object countryCode;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("CountryNameEn")
    @Expose
    private Object countryNameEn;
    @SerializedName("CountryNameFa")
    @Expose
    private Object countryNameFa;
    @SerializedName("Icon")
    @Expose
    private Object icon;
    @SerializedName("IsCharter")
    @Expose
    private Boolean isCharter;
    @SerializedName("IsDemostic")
    @Expose
    private Boolean isDemostic;
    @SerializedName("IsPersian")
    @Expose
    private Object isPersian;
    @SerializedName("LoadPropertiesFromID")
    @Expose
    private Object loadPropertiesFromID;
    @SerializedName("ParentId")
    @Expose
    private String parentId;
    @SerializedName("ParentItem")
    @Expose
    private Object parentItem;
    @SerializedName("StandardName")
    @Expose
    private Object standardName;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Object getAirportNameEn() {
        return airportNameEn;
    }

    public void setAirportNameEn(Object airportNameEn) {
        this.airportNameEn = airportNameEn;
    }

    public Object getAirportNameFa() {
        return airportNameFa;
    }

    public void setAirportNameFa(Object airportNameFa) {
        this.airportNameFa = airportNameFa;
    }

    public Object getCityCode() {
        return cityCode;
    }

    public void setCityCode(Object cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Object getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(Object cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public Object getCityNameFa() {
        return cityNameFa;
    }

    public void setCityNameFa(Object cityNameFa) {
        this.cityNameFa = cityNameFa;
    }

    public Object getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Object countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Object getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(Object countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public Object getCountryNameFa() {
        return countryNameFa;
    }

    public void setCountryNameFa(Object countryNameFa) {
        this.countryNameFa = countryNameFa;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public Boolean getIsCharter() {
        return isCharter;
    }

    public void setIsCharter(Boolean isCharter) {
        this.isCharter = isCharter;
    }

    public Boolean getIsDemostic() {
        return isDemostic;
    }

    public void setIsDemostic(Boolean isDemostic) {
        this.isDemostic = isDemostic;
    }

    public Object getIsPersian() {
        return isPersian;
    }

    public void setIsPersian(Object isPersian) {
        this.isPersian = isPersian;
    }

    public Object getLoadPropertiesFromID() {
        return loadPropertiesFromID;
    }

    public void setLoadPropertiesFromID(Object loadPropertiesFromID) {
        this.loadPropertiesFromID = loadPropertiesFromID;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Object getParentItem() {
        return parentItem;
    }

    public void setParentItem(Object parentItem) {
        this.parentItem = parentItem;
    }

    public Object getStandardName() {
        return standardName;
    }

    public void setStandardName(Object standardName) {
        this.standardName = standardName;
    }

}
