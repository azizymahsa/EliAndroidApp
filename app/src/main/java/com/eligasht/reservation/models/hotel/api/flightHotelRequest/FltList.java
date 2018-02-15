package com.eligasht.reservation.models.hotel.api.flightHotelRequest;

/**
 * Created by Reza.nejati on 1/16/2018.
 */

public class FltList {
    public final String AirlineNameEn;
    public final String AirlineNameFa;
    public final String FlightArrivalTime;
    public final String FlightTime;
    public final String AirlineCode;
    public final boolean IsDepartureSegment;
    public final String ArrivalAirportCode;
    public final String FltDurationH;
    public final String FltDurationM;
    public final String FlightNumber;

    public FltList(String airlineNameEn, String airlineNameFa, String flightArrivalTime, String flightTime, String airlineCode, boolean isDepartureSegment, String arrivalAirportCode, String fltDurationH, String fltDurationM, String flightNumber) {
        AirlineNameEn = airlineNameEn;
        AirlineNameFa = airlineNameFa;
        FlightArrivalTime = flightArrivalTime;
        FlightTime = flightTime;
        AirlineCode = airlineCode;
        IsDepartureSegment = isDepartureSegment;
        ArrivalAirportCode = arrivalAirportCode;
        FltDurationH = fltDurationH;
        FltDurationM = fltDurationM;
        FlightNumber = flightNumber;
    }
}
