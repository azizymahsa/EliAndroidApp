package com.eligasht.reservation.models.model;

/**
 * Created by Mahsa.azizi on 1/27/2018.
 */

public class PinModelDetail {

private long AdlBaseFare;
private long Taxes;
private long TotalFare;
private String FlightTimeR;
private  String FlightArrivalTimeR;

private String DepartureCityNameFa;
private String DepartureAirportNameFaR;

private String ArrivalCityNameFa;
private  String ArrivalAirportNameFaR;

private  String AirlineCode;
private String FlightNumberR;
private  String AirlineNameFaR;

private int sizeEpnd;

    public PinModelDetail(long adlBaseFare, long taxes, long totalFare, String flightTimeR, String flightArrivalTimeR, String departureCityNameFa, String departureAirportNameFaR, String arrivalCityNameFa, String arrivalAirportNameFaR, String airlineCode, String flightNumberR, String airlineNameFaR, int sizeEpnd) {
        AdlBaseFare = adlBaseFare;
        Taxes = taxes;
        TotalFare = totalFare;
        FlightTimeR = flightTimeR;
        FlightArrivalTimeR = flightArrivalTimeR;
        DepartureCityNameFa = departureCityNameFa;
        DepartureAirportNameFaR = departureAirportNameFaR;
        ArrivalCityNameFa = arrivalCityNameFa;
        ArrivalAirportNameFaR = arrivalAirportNameFaR;
        AirlineCode = airlineCode;
        FlightNumberR = flightNumberR;
        AirlineNameFaR = airlineNameFaR;
        this.sizeEpnd = sizeEpnd;
    }


    public long getAdlBaseFare() {
        return AdlBaseFare;
    }

    public void setAdlBaseFare(long adlBaseFare) {
        AdlBaseFare = adlBaseFare;
    }

    public long getTaxes() {
        return Taxes;
    }

    public void setTaxes(long taxes) {
        Taxes = taxes;
    }

    public long getTotalFare() {
        return TotalFare;
    }

    public void setTotalFare(long totalFare) {
        TotalFare = totalFare;
    }

    public String getFlightTimeR() {
        return FlightTimeR;
    }

    public void setFlightTimeR(String flightTimeR) {
        FlightTimeR = flightTimeR;
    }

    public String getFlightArrivalTimeR() {
        return FlightArrivalTimeR;
    }

    public void setFlightArrivalTimeR(String flightArrivalTimeR) {
        FlightArrivalTimeR = flightArrivalTimeR;
    }

    public String getDepartureCityNameFa() {
        return DepartureCityNameFa;
    }

    public void setDepartureCityNameFa(String departureCityNameFa) {
        DepartureCityNameFa = departureCityNameFa;
    }

    public String getDepartureAirportNameFaR() {
        return DepartureAirportNameFaR;
    }

    public void setDepartureAirportNameFaR(String departureAirportNameFaR) {
        DepartureAirportNameFaR = departureAirportNameFaR;
    }

    public String getArrivalCityNameFa() {
        return ArrivalCityNameFa;
    }

    public void setArrivalCityNameFa(String arrivalCityNameFa) {
        ArrivalCityNameFa = arrivalCityNameFa;
    }

    public String getArrivalAirportNameFaR() {
        return ArrivalAirportNameFaR;
    }

    public void setArrivalAirportNameFaR(String arrivalAirportNameFaR) {
        ArrivalAirportNameFaR = arrivalAirportNameFaR;
    }

    public String getAirlineCode() {
        return AirlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        AirlineCode = airlineCode;
    }

    public String getFlightNumberR() {
        return FlightNumberR;
    }

    public void setFlightNumberR(String flightNumberR) {
        FlightNumberR = flightNumberR;
    }

    public String getAirlineNameFaR() {
        return AirlineNameFaR;
    }

    public void setAirlineNameFaR(String airlineNameFaR) {
        AirlineNameFaR = airlineNameFaR;
    }

    public int getSizeEpnd() {
        return sizeEpnd;
    }

    public void setSizeEpnd(int sizeEpnd) {
        this.sizeEpnd = sizeEpnd;
    }
}
