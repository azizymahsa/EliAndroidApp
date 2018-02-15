package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

/**
 * Created by Reza.nejati on 2/14/2018.
 */

public class TotalPrice {

        private String Amount;

        private String CurrencyCode;

    public String getAmount ()
    {
        return Amount;
    }

    public void setAmount (String Amount)
    {
        this.Amount = Amount;
    }

    public String getCurrencyCode ()
    {
        return CurrencyCode;
    }

    public void setCurrencyCode (String CurrencyCode)
    {
        this.CurrencyCode = CurrencyCode;
    }


}