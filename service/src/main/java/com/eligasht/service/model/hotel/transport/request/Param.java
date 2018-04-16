
package com.eligasht.service.model.hotel.transport.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Param {

    @SerializedName("HasHotel")
    @Expose
    private String hasHotel;
    @SerializedName("PassengerAirportCode")
    @Expose
    private String passengerAirportCode;
    @SerializedName("PassengerArrDate")
    @Expose
    private String passengerArrDate;
    @SerializedName("PassengerArrFltNo")
    @Expose
    private String passengerArrFltNo;
    @SerializedName("PassengerArrTime")
    @Expose
    private String passengerArrTime;
    @SerializedName("PassengerCityCode")
    @Expose
    private String passengerCityCode;
    @SerializedName("PassengerDepAirport")
    @Expose
    private String passengerDepAirport;
    @SerializedName("PassengerDepDate")
    @Expose
    private String passengerDepDate;
    @SerializedName("PassengerDepFltNo")
    @Expose
    private String passengerDepFltNo;
    @SerializedName("PassengerDepTime")
    @Expose
    private String passengerDepTime;
    @SerializedName("PassengerHotelId")
    @Expose
    private String passengerHotelId;
    @SerializedName("PassengerList")
    @Expose
    private String passengerList;
    @SerializedName("ServiceID")
    @Expose
    private String serviceID;

    public String getHasHotel() {
        return hasHotel;
    }

    public void setHasHotel(String hasHotel) {
        this.hasHotel = hasHotel;
    }

    public String getPassengerAirportCode() {
        return passengerAirportCode;
    }

    public void setPassengerAirportCode(String passengerAirportCode) {
        this.passengerAirportCode = passengerAirportCode;
    }

    public String getPassengerArrDate() {
        return passengerArrDate;
    }

    public void setPassengerArrDate(String passengerArrDate) {
        this.passengerArrDate = passengerArrDate;
    }

    public String getPassengerArrFltNo() {
        return passengerArrFltNo;
    }

    public void setPassengerArrFltNo(String passengerArrFltNo) {
        this.passengerArrFltNo = passengerArrFltNo;
    }

    public String getPassengerArrTime() {
        return passengerArrTime;
    }

    public void setPassengerArrTime(String passengerArrTime) {
        this.passengerArrTime = passengerArrTime;
    }

    public String getPassengerCityCode() {
        return passengerCityCode;
    }

    public void setPassengerCityCode(String passengerCityCode) {
        this.passengerCityCode = passengerCityCode;
    }

    public String getPassengerDepAirport() {
        return passengerDepAirport;
    }

    public void setPassengerDepAirport(String passengerDepAirport) {
        this.passengerDepAirport = passengerDepAirport;
    }

    public String getPassengerDepDate() {
        return passengerDepDate;
    }

    public void setPassengerDepDate(String passengerDepDate) {
        this.passengerDepDate = passengerDepDate;
    }

    public String getPassengerDepFltNo() {
        return passengerDepFltNo;
    }

    public void setPassengerDepFltNo(String passengerDepFltNo) {
        this.passengerDepFltNo = passengerDepFltNo;
    }

    public String getPassengerDepTime() {
        return passengerDepTime;
    }

    public void setPassengerDepTime(String passengerDepTime) {
        this.passengerDepTime = passengerDepTime;
    }

    public String getPassengerHotelId() {
        return passengerHotelId;
    }

    public void setPassengerHotelId(String passengerHotelId) {
        this.passengerHotelId = passengerHotelId;
    }

    public String getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(String passengerList) {
        this.passengerList = passengerList;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

}
