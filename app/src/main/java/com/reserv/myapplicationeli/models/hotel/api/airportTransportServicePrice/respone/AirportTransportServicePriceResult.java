package com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.respone;

import com.reserv.myapplicationeli.models.hotel.api.Errors;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class AirportTransportServicePriceResult {
    public final ArrayList<Errors> Errors;

    public AirportTransportServicePriceResult(ArrayList<com.reserv.myapplicationeli.models.hotel.api.Errors> errors) {
        Errors = errors;
    }
}
