package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class AirportTransportRespone {

        private AirportTransportServicePriceResult AirportTransportServicePriceResult;

    public AirportTransportServicePriceResult getAirportTransportServicePriceResult ()
    {
        return AirportTransportServicePriceResult;
    }

    public void setAirportTransportServicePriceResult (AirportTransportServicePriceResult AirportTransportServicePriceResult)
    {
        this.AirportTransportServicePriceResult = AirportTransportServicePriceResult;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [AirportTransportServicePriceResult = "+AirportTransportServicePriceResult+"]";
    }
}
