package com.eligasht.reservation.models.hotel.api.changeflight.request;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 2/21/2018.
 */

public class Request {
   public final String FlightId;
   public final String SearchKey;
   public final String Culture;
   public final Identity identity;

    public Request(String flightId, String searchKey, String culture, Identity identity) {
        FlightId = flightId;
        SearchKey = searchKey;
        Culture = culture;
        this.identity = identity;
    }
}
