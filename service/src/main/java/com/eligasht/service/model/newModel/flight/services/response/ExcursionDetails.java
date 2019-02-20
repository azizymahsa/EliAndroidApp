
package com.eligasht.service.model.newModel.flight.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExcursionDetails {

/*    public ExcursionDetails(String arrialAirportCode, String arrialAirportName, String departureFltNo, String arrivalFltNo, String departureFltDate, String arrivalFltDate, String departureFltTime, String arrivalFltTime, Integer hotelID, String hotelNameEn, String passengerList, Integer cityID, Boolean loadDB) {
        this.arrialAirportCode = arrialAirportCode;
        this.arrialAirportName = arrialAirportName;
        this.departureFltNo = departureFltNo;
        this.arrivalFltNo = arrivalFltNo;
        this.departureFltDate = departureFltDate;
        this.arrivalFltDate = arrivalFltDate;
        this.departureFltTime = departureFltTime;
        this.arrivalFltTime = arrivalFltTime;
        this.hotelID = hotelID;
        this.hotelNameEn = hotelNameEn;
        this.passengerList = passengerList;
        this.cityID = cityID;
        this.loadDB = loadDB;
    }*/
public ExcursionDetails(String arrialAirportCode, String arrialAirportName, String arrivalFltDate, String arrivalFltNo, String arrivalFltTime, Integer cityID, String departureFltDate, String departureFltNo, String departureFltTime, Integer hotelID, String hotelNameEn, String passengerList) {
    this.arrialAirportCode = arrialAirportCode;
    this.arrialAirportName = arrialAirportName;
    this.departureFltNo = departureFltNo;
    this.arrivalFltNo = arrivalFltNo;
    this.departureFltDate = departureFltDate;
    this.arrivalFltDate = arrivalFltDate;
    this.departureFltTime = departureFltTime;
    this.arrivalFltTime = arrivalFltTime;
    this.hotelID = hotelID;
    this.hotelNameEn = hotelNameEn;
    this.passengerList = passengerList;
    this.cityID = cityID;
    this.loadDB = loadDB;
}
    public ExcursionDetails(String arrialAirportCode, String arrialAirportName, String arrivalFltDate, String arrivalFltNo, String arrivalFltTime, String cityID, String departureFltDate, String departureFltNo, String departureFltTime, String hotelID, String hotelNameEn, String passengerList) {
        this.arrialAirportCode = arrialAirportCode;
        this.arrialAirportName = arrialAirportName;
        this.departureFltNo = departureFltNo;
        this.arrivalFltNo = arrivalFltNo;
        this.departureFltDate = departureFltDate;
        this.arrivalFltDate = arrivalFltDate;
        this.departureFltTime = departureFltTime;
        this.arrivalFltTime = arrivalFltTime;
        this.cityID = Integer.parseInt(cityID);
        this.passengerList = passengerList;
        this.hotelID = Integer.parseInt(hotelID);
        this.hotelNameEn = hotelNameEn;

    }
    @SerializedName("ArrialAirportCode")

    @Expose
    private String arrialAirportCode;
    @SerializedName("ArrialAirportName")
    @Expose
    private String arrialAirportName;
    @SerializedName("DepartureFltNo")
    @Expose
    private String departureFltNo;
    @SerializedName("ArrivalFltNo")
    @Expose
    private String arrivalFltNo;
    @SerializedName("DepartureFltDate")
    @Expose
    private String departureFltDate;
    @SerializedName("ArrivalFltDate")
    @Expose
    private String arrivalFltDate;
    @SerializedName("DepartureFltTime")
    @Expose
    private String departureFltTime;
    @SerializedName("ArrivalFltTime")
    @Expose
    private String arrivalFltTime;
    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("HotelNameEn")
    @Expose
    private String hotelNameEn;
    @SerializedName("PassengerList")
    @Expose
    private String passengerList;
    @SerializedName("CityID")
    @Expose
    private Integer cityID;
    @SerializedName("LoadDB")
    @Expose
    private Boolean loadDB;




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

    public String getDepartureFltNo() {
        return departureFltNo;
    }

    public void setDepartureFltNo(String departureFltNo) {
        this.departureFltNo = departureFltNo;
    }

    public String getArrivalFltNo() {
        return arrivalFltNo;
    }

    public void setArrivalFltNo(String arrivalFltNo) {
        this.arrivalFltNo = arrivalFltNo;
    }

    public String getDepartureFltDate() {
        return departureFltDate;
    }

    public void setDepartureFltDate(String departureFltDate) {
        this.departureFltDate = departureFltDate;
    }

    public String getArrivalFltDate() {
        return arrivalFltDate;
    }

    public void setArrivalFltDate(String arrivalFltDate) {
        this.arrivalFltDate = arrivalFltDate;
    }

    public String getDepartureFltTime() {
        return departureFltTime;
    }

    public void setDepartureFltTime(String departureFltTime) {
        this.departureFltTime = departureFltTime;
    }

    public String getArrivalFltTime() {
        return arrivalFltTime;
    }

    public void setArrivalFltTime(String arrivalFltTime) {
        this.arrivalFltTime = arrivalFltTime;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(String hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public String getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(String passengerList) {
        this.passengerList = passengerList;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Boolean getLoadDB() {
        return loadDB;
    }

    public void setLoadDB(Boolean loadDB) {
        this.loadDB = loadDB;
    }

}
