
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ChildTotalFare {

    @SerializedName("Amount")
    @Expose
    private Double amount;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }



}
