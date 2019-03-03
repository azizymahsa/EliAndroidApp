
package com.eligasht.service.model.newModel.flight.prefactor.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {

    @SerializedName("FlightID")
    @Expose
    private Integer flightID;
    @SerializedName("DepartureCityCode")
    @Expose
    private Object departureCityCode;
    @SerializedName("ArrivalCityCode")
    @Expose
    private Object arrivalCityCode;
    @SerializedName("DepartureCityFa")
    @Expose
    private String departureCityFa;
    @SerializedName("ArrivalCityFa")
    @Expose
    private String arrivalCityFa;
    @SerializedName("DepartureCityEn")
    @Expose
    private String departureCityEn;
    @SerializedName("ArrivalCityEn")
    @Expose
    private String arrivalCityEn;
    @SerializedName("FltDate")
    @Expose
    private String fltDate;
    @SerializedName("FltTime")
    @Expose
    private String fltTime;
    @SerializedName("FltCheckinTime")
    @Expose
    private String fltCheckinTime;
    @SerializedName("FltCheckinDate")
    @Expose
    private Object fltCheckinDate;
    @SerializedName("AirlineNameFa")
    @Expose
    private String airlineNameFa;
    @SerializedName("AirlineNameEn")
    @Expose
    private String airlineNameEn;
    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("FltNumber")
    @Expose
    private String fltNumber;
    @SerializedName("AirplaneFa")
    @Expose
    private String airplaneFa;
    @SerializedName("AirplaneEn")
    @Expose
    private String airplaneEn;
    @SerializedName("CabinClassCode")
    @Expose
    private String cabinClassCode;
    @SerializedName("CabinClassName")
    @Expose
    private String cabinClassName;
    @SerializedName("CabinClassNameFa")
    @Expose
    private String cabinClassNameFa;
    @SerializedName("DepAirPortCode")
    @Expose
    private Object depAirPortCode;
    @SerializedName("ArrAirPortCode")
    @Expose
    private Object arrAirPortCode;
    @SerializedName("DepAirPortEn")
    @Expose
    private String depAirPortEn;
    @SerializedName("ArrAirPortEn")
    @Expose
    private String arrAirPortEn;
    @SerializedName("DepAirPortFa")
    @Expose
    private String depAirPortFa;
    @SerializedName("ArrAirPortFa")
    @Expose
    private String arrAirPortFa;
    @SerializedName("RqHotelID")
    @Expose
    private Integer rqHotelID;
    @SerializedName("Supplier")
    @Expose
    private String supplier;

    public Integer getFlightID() {
        return flightID;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public Object getDepartureCityCode() {
        return departureCityCode;
    }

    public void setDepartureCityCode(Object departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    public Object getArrivalCityCode() {
        return arrivalCityCode;
    }

    public void setArrivalCityCode(Object arrivalCityCode) {
        this.arrivalCityCode = arrivalCityCode;
    }

    public String getDepartureCityFa() {
        return departureCityFa;
    }

    public void setDepartureCityFa(String departureCityFa) {
        this.departureCityFa = departureCityFa;
    }

    public String getArrivalCityFa() {
        return arrivalCityFa;
    }

    public void setArrivalCityFa(String arrivalCityFa) {
        this.arrivalCityFa = arrivalCityFa;
    }

    public String getDepartureCityEn() {
        return departureCityEn;
    }

    public void setDepartureCityEn(String departureCityEn) {
        this.departureCityEn = departureCityEn;
    }

    public String getArrivalCityEn() {
        return arrivalCityEn;
    }

    public void setArrivalCityEn(String arrivalCityEn) {
        this.arrivalCityEn = arrivalCityEn;
    }

    public String getFltDate() {
        return fltDate;
    }

    public void setFltDate(String fltDate) {
        this.fltDate = fltDate;
    }

    public String getFltTime() {
        return fltTime;
    }

    public void setFltTime(String fltTime) {
        this.fltTime = fltTime;
    }

    public String getFltCheckinTime() {
        return fltCheckinTime;
    }

    public void setFltCheckinTime(String fltCheckinTime) {
        this.fltCheckinTime = fltCheckinTime;
    }

    public Object getFltCheckinDate() {
        return fltCheckinDate;
    }

    public void setFltCheckinDate(Object fltCheckinDate) {
        this.fltCheckinDate = fltCheckinDate;
    }

    public String getAirlineNameFa() {
        return airlineNameFa;
    }

    public void setAirlineNameFa(String airlineNameFa) {
        this.airlineNameFa = airlineNameFa;
    }

    public String getAirlineNameEn() {
        return airlineNameEn;
    }

    public void setAirlineNameEn(String airlineNameEn) {
        this.airlineNameEn = airlineNameEn;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFltNumber() {
        return fltNumber;
    }

    public void setFltNumber(String fltNumber) {
        this.fltNumber = fltNumber;
    }

    public String getAirplaneFa() {
        return airplaneFa;
    }

    public void setAirplaneFa(String airplaneFa) {
        this.airplaneFa = airplaneFa;
    }

    public String getAirplaneEn() {
        return airplaneEn;
    }

    public void setAirplaneEn(String airplaneEn) {
        this.airplaneEn = airplaneEn;
    }

    public String getCabinClassCode() {
        return cabinClassCode;
    }

    public void setCabinClassCode(String cabinClassCode) {
        this.cabinClassCode = cabinClassCode;
    }

    public String getCabinClassName() {
        return cabinClassName;
    }

    public void setCabinClassName(String cabinClassName) {
        this.cabinClassName = cabinClassName;
    }

    public String getCabinClassNameFa() {
        return cabinClassNameFa;
    }

    public void setCabinClassNameFa(String cabinClassNameFa) {
        this.cabinClassNameFa = cabinClassNameFa;
    }

    public Object getDepAirPortCode() {
        return depAirPortCode;
    }

    public void setDepAirPortCode(Object depAirPortCode) {
        this.depAirPortCode = depAirPortCode;
    }

    public Object getArrAirPortCode() {
        return arrAirPortCode;
    }

    public void setArrAirPortCode(Object arrAirPortCode) {
        this.arrAirPortCode = arrAirPortCode;
    }

    public String getDepAirPortEn() {
        return depAirPortEn;
    }

    public void setDepAirPortEn(String depAirPortEn) {
        this.depAirPortEn = depAirPortEn;
    }

    public String getArrAirPortEn() {
        return arrAirPortEn;
    }

    public void setArrAirPortEn(String arrAirPortEn) {
        this.arrAirPortEn = arrAirPortEn;
    }

    public String getDepAirPortFa() {
        return depAirPortFa;
    }

    public void setDepAirPortFa(String depAirPortFa) {
        this.depAirPortFa = depAirPortFa;
    }

    public String getArrAirPortFa() {
        return arrAirPortFa;
    }

    public void setArrAirPortFa(String arrAirPortFa) {
        this.arrAirPortFa = arrAirPortFa;
    }

    public Integer getRqHotelID() {
        return rqHotelID;
    }

    public void setRqHotelID(Integer rqHotelID) {
        this.rqHotelID = rqHotelID;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

}
