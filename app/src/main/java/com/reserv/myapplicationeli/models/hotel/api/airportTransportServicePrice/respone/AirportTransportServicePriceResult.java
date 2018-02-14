package com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.respone;

import com.reserv.myapplicationeli.models.hotel.api.Errors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class AirportTransportServicePriceResult {
    public final ArrayList<Errors> Errors;
    public final TransferAvailabilityRoundtripResults[] TransferAvailabilityRoundtripResults;

    public AirportTransportServicePriceResult(ArrayList<com.reserv.myapplicationeli.models.hotel.api.Errors> errors, com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.respone.TransferAvailabilityRoundtripResults[] transferAvailabilityRoundtripResults) {
        Errors = errors;
        TransferAvailabilityRoundtripResults = transferAvailabilityRoundtripResults;
    }
}
