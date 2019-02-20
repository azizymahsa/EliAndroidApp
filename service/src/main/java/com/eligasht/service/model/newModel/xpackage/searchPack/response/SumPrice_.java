
package com.eligasht.service.model.newModel.xpackage.searchPack.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SumPrice_ {

    @SerializedName("CurrencyCode")
    @Expose
    private Object currencyCode;
    @SerializedName("Amount")
    @Expose
    private Integer amount;

    public Object getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
