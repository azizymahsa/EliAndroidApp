
package com.eligasht.service.model.flight.response.PurchaseFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExcursionDta {

    @SerializedName("ArrialAirportCode")
    @Expose
    private String arrialAirportCode;
    @SerializedName("ArrialAirportName")
    @Expose
    private String arrialAirportName;
    @SerializedName("ArrivalFltDate")
    @Expose
    private String arrivalFltDate;
    @SerializedName("ArrivalFltNo")
    @Expose
    private String arrivalFltNo;
    @SerializedName("ArrivalFltTime")
    @Expose
    private String arrivalFltTime;
    @SerializedName("CityID")
    @Expose
    private Integer cityID;
    @SerializedName("DepartureFltDate")
    @Expose
    private String departureFltDate;
    @SerializedName("DepartureFltNo")
    @Expose
    private String departureFltNo;
    @SerializedName("DepartureFltTime")
    @Expose
    private String departureFltTime;
    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("HotelNameEn")
    @Expose
    private Object hotelNameEn;
    @SerializedName("LoadDB")
    @Expose
    private Boolean loadDB;
    @SerializedName("PassengerList")
    @Expose
    private String passengerList;

    public String getArrialAirportCode() {
        return arrialAirportCode;
    }

    public void setArrialAirportCode(String arrialAirportCode) {
        this.arrialAirportCode = arrialAirportCode;
    }

    public String getArrialAirportName() {
        return arrialAirportName;
    }

    public void setArrialAirportName(String arrialAirportName) {
        this.arrialAirportName = arrialAirportName;
    }

    public String getArrivalFltDate() {
        return arrivalFltDate;
    }

    public void setArrivalFltDate(String arrivalFltDate) {
        this.arrivalFltDate = arrivalFltDate;
    }

    public String getArrivalFltNo() {
        return arrivalFltNo;
    }

    public void setArrivalFltNo(String arrivalFltNo) {
        this.arrivalFltNo = arrivalFltNo;
    }

    public String getArrivalFltTime() {
        return arrivalFltTime;
    }

    public void setArrivalFltTime(String arrivalFltTime) {
        this.arrivalFltTime = arrivalFltTime;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getDepartureFltDate() {
        return departureFltDate;
    }

    public void setDepartureFltDate(String departureFltDate) {
        this.departureFltDate = departureFltDate;
    }

    public String getDepartureFltNo() {
        return departureFltNo;
    }

    public void setDepartureFltNo(String departureFltNo) {
        this.departureFltNo = departureFltNo;
    }

    public String getDepartureFltTime() {
        return departureFltTime;
    }

    public void setDepartureFltTime(String departureFltTime) {
        this.departureFltTime = departureFltTime;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Object getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(Object hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public Boolean getLoadDB() {
        return loadDB;
    }

    public void setLoadDB(Boolean loadDB) {
        this.loadDB = loadDB;
    }

    public String getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(String passengerList) {
        this.passengerList = passengerList;
    }

}
