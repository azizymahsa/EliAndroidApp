
package com.eligasht.service.model.flight.response.searchFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalFare {

    @SerializedName("Amount")
    @Expose
    private double amount;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
