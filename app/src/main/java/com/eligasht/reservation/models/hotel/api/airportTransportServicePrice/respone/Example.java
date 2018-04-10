
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Example {

    @SerializedName("AirportTransportServicePriceResult")
    @Expose
    private AirportTransportServicePriceResult airportTransportServicePriceResult;

    public AirportTransportServicePriceResult getAirportTransportServicePriceResult() {
        return airportTransportServicePriceResult;
    }

    public void setAirportTransportServicePriceResult(AirportTransportServicePriceResult airportTransportServicePriceResult) {
        this.airportTransportServicePriceResult = airportTransportServicePriceResult;
    }



}
