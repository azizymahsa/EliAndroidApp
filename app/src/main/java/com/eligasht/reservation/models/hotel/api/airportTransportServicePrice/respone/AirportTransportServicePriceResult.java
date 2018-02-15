package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class AirportTransportServicePriceResult {
    public final ArrayList<com.eligasht.reservation.models.hotel.api.Errors> Errors;
    public final TransferAvailabilityRoundtripResults[] TransferAvailabilityRoundtripResults;

    public AirportTransportServicePriceResult(ArrayList<com.eligasht.reservation.models.hotel.api.Errors> errors, com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone.TransferAvailabilityRoundtripResults[] transferAvailabilityRoundtripResults) {
        Errors = errors;
        TransferAvailabilityRoundtripResults = transferAvailabilityRoundtripResults;
    }
}
