package com.eligasht.reservation.models.hotel.api.flightchange;

import com.eligasht.reservation.models.hotel.api.flightHotelRequest.Flights;
import com.eligasht.reservation.models.model.Errors;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 2/21/2018.
 */

public class LoadFlightResult {
    public final ArrayList<com.eligasht.reservation.models.hotel.api.Errors> Error;
    public final com.eligasht.reservation.models.hotel.api.flightHotelRequest.Flights HFlight;

    public LoadFlightResult(ArrayList<com.eligasht.reservation.models.hotel.api.Errors> error, Flights HFlight) {
        Error = error;
        this.HFlight = HFlight;
    }
}
