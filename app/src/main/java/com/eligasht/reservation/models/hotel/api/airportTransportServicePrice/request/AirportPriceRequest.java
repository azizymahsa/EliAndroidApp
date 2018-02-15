package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.request;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class AirportPriceRequest {
    public final String TmpRq;
    public final Param Param;
    public final String Culture;
    public final Identity identity;

    public AirportPriceRequest(String tmpRq, com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.request.Param param, String culture, Identity identity) {
        TmpRq = tmpRq;
        Param = param;
        Culture = culture;
        this.identity = identity;
    }
}
