
package com.eligasht.service.model.newModel.flight.searchFlight.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfBaseFare {

    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("Amount")
    @Expose
    private Integer amount;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
