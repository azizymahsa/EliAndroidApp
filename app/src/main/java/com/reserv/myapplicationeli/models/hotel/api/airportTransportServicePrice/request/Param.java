package com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.request;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class Param {
    public final String PassengerDepAirport;
    public final String PassengerAirportCode;
    public final String PassengerDepDate;
    public final String PassengerDepTime;
    public final String PassengerDepFltNo;
    public final String PassengerArrDate;
    public final String ServiceID;
    public final String PassengerArrTime;
    public final String PassengerArrFltNo;
    public final String PassengerCityCode;
    public final String PassengerHotelId;
    public final String PassengerList;
    public final String HasHotel;

    public Param(String passengerDepAirport, String passengerAirportCode, String passengerDepDate, String passengerDepTime, String passengerDepFltNo, String passengerArrDate, String serviceID, String passengerArrTime, String passengerArrFltNo, String passengerCityCode, String passengerHotelId, String passengerList, String hasHotel) {
        PassengerDepAirport = passengerDepAirport;
        PassengerAirportCode = passengerAirportCode;
        PassengerDepDate = passengerDepDate;
        PassengerDepTime = passengerDepTime;
        PassengerDepFltNo = passengerDepFltNo;
        PassengerArrDate = passengerArrDate;
        ServiceID = serviceID;
        PassengerArrTime = passengerArrTime;
        PassengerArrFltNo = passengerArrFltNo;
        PassengerCityCode = passengerCityCode;
        PassengerHotelId = passengerHotelId;
        PassengerList = passengerList;
        HasHotel = hasHotel;
    }
}
