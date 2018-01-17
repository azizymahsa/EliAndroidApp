package com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/16/2018.
 */

public class Flights {
    public final ArrayList<com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest.FltList> FltList;
    public final String ArrRout;
    public final String DepRout;

    public Flights(ArrayList<com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest.FltList> fltList, String arrRout, String depRout) {
        FltList = fltList;
        ArrRout = arrRout;
        DepRout = depRout;
    }
}
