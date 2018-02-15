package com.eligasht.reservation.models.hotel.api.flightHotelRequest;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/16/2018.
 */

public class Flights {
    public final ArrayList<com.eligasht.reservation.models.hotel.api.flightHotelRequest.FltList> FltList;
    public final String ArrRout;
    public final String DepRout;
    public final String FlightID;
    public final String Amount;

    public Flights(ArrayList<com.eligasht.reservation.models.hotel.api.flightHotelRequest.FltList> fltList, String arrRout, String depRout, String flightID, String amount) {
        FltList = fltList;
        ArrRout = arrRout;
        DepRout = depRout;
        FlightID = flightID;
        Amount = amount;
    }
}
