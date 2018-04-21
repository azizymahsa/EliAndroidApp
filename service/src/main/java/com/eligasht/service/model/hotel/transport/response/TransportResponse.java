
package com.eligasht.service.model.hotel.transport.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransportResponse {

    @SerializedName("AirportTransportServicePriceResult")
    @Expose
    public AirportTransportServicePriceResult airportTransportServicePriceResult;

    public AirportTransportServicePriceResult getAirportTransportServicePriceResult() {
        return airportTransportServicePriceResult;
    }

    public void setAirportTransportServicePriceResult(AirportTransportServicePriceResult airportTransportServicePriceResult) {
        this.airportTransportServicePriceResult = airportTransportServicePriceResult;
    }

}
