
package com.eligasht.service.model.hotel.getHotelList.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("CityID")
    @Expose
    private Integer cityID;
    @SerializedName("CityNameEn")
    @Expose
    private String cityNameEn;
    @SerializedName("CityNameFa")
    @Expose
    private String cityNameFa;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("CountryNameEn")
    @Expose
    private Object countryNameEn;
    @SerializedName("CountryNameFa")
    @Expose
    private Object countryNameFa;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public String getCityNameFa() {
        return cityNameFa;
    }

    public void setCityNameFa(String cityNameFa) {
        this.cityNameFa = cityNameFa;
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

}
