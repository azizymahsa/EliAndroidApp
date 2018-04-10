
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InfantTotalFare {

    @SerializedName("Amount")
    @Expose
    private Integer amount;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }



}
