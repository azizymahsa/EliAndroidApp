package com.eligasht.reservation.models;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class FlightPreFactorModel {
    String AirlineNameFa;
    String ArrAirPortFa;
    String DepAirPortFa;
    String FltDate;
    String FltCheckinTime;
    String FltTime;
    String FltNumber;
    String AirlineNameEn;
    String DepartureCityFa;
    String AirlineCode;
    String arrivalCityFa;

    public FlightPreFactorModel(String airlineNameFa, String arrAirPortFa, String depAirPortFa, String fltDate, String fltCheckinTime, String fltTime, String fltNumber, String airlineNameEn, String departureCityFa, String airlineCode, String arrivalCityFa) {
        AirlineNameFa = airlineNameFa;
        ArrAirPortFa = arrAirPortFa;
        DepAirPortFa = depAirPortFa;
        FltDate = fltDate;
        FltCheckinTime = fltCheckinTime;
        FltTime = fltTime;
        FltNumber = fltNumber;
        AirlineNameEn = airlineNameEn;
        DepartureCityFa = departureCityFa;
        AirlineCode = airlineCode;
        this.arrivalCityFa = arrivalCityFa;
    }

    public String getAirlineNameFa() {
        return AirlineNameFa;
    }

    public void setAirlineNameFa(String airlineNameFa) {
        AirlineNameFa = airlineNameFa;
    }

    public String getArrAirPortFa() {
        return ArrAirPortFa;
    }

    public void setArrAirPortFa(String arrAirPortFa) {
        ArrAirPortFa = arrAirPortFa;
    }

    public String getDepAirPortFa() {
        return DepAirPortFa;
    }

    public void setDepAirPortFa(String depAirPortFa) {
        DepAirPortFa = depAirPortFa;
    }

    public String getFltDate() {
        return FltDate;
    }

    public void setFltDate(String fltDate) {
        FltDate = fltDate;
    }

    public String getFltCheckinTime() {
        return FltCheckinTime;
    }

    public void setFltCheckinTime(String fltCheckinTime) {
        FltCheckinTime = fltCheckinTime;
    }
    public String getFltTime() {
        return FltTime;
    }
    public void setFltTime(String fltTime) {
        FltTime = fltTime;
    }

    public String getFltNumber() {
        return FltNumber;
    }

    public void setFltNumber(String fltNumber) {
        FltNumber = fltNumber;
    }

    public String getAirlineNameEn() {
        return AirlineNameEn;
    }

    public void setAirlineNameEn(String airlineNameEn) {
        AirlineNameEn = airlineNameEn;
    }

    public String getDepartureCityFa() {
        return DepartureCityFa;
    }

    public void setDepartureCityFa(String departureCityFa) {
        DepartureCityFa = departureCityFa;
    }

    public String getAirlineCode() {
        return AirlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        AirlineCode = airlineCode;
    }

    public String getArrivalCityFa() {
        return arrivalCityFa;
    }

    public void setArrivalCityFa(String arrivalCityFa) {
        this.arrivalCityFa = arrivalCityFa;
    }
}

