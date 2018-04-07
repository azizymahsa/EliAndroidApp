
package com.eligasht.service.model.flight.response.PurchaseFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePurchaseFlight {

    @SerializedName("PurchaseFlightResult")
    @Expose
    private PurchaseFlightResult purchaseFlightResult;

    public PurchaseFlightResult getPurchaseFlightResult() {
        return purchaseFlightResult;
    }

    public void setPurchaseFlightResult(PurchaseFlightResult purchaseFlightResult) {
        this.purchaseFlightResult = purchaseFlightResult;
    }

}
