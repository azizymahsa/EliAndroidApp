
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("airportTransportServicePriceResult", airportTransportServicePriceResult).toString();
    }

}
