
package com.eligasht.service.model.newModel.flight.searchFlight.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalFareCost {

    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("Amount")
    @Expose
    private Double amount;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
