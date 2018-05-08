package com.eligasht.reservation.models;

/**
 * Created by hoseinraeisi on 1/15/18.
 */

public class Country {

    private String AirportName;
    private String CityName;
    private String CityCode;
    private String AirportCode;
    private String AirportID;
    private String ParentId;
    private String CityNameEn;

    public String getAirportName() {
        return AirportName;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getAirportCode() {
        return AirportCode;
    }

    public void setAirportCode(String airportCode) {
        AirportCode = airportCode;
    }

    public String getAirportID() {
        return AirportID;
    }

    public void setAirportID(String airportID) {
        AirportID = airportID;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getCityNameEn() {
        return CityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        CityNameEn = cityNameEn;
    }
}
