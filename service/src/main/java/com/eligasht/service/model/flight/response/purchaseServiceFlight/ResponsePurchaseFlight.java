
package com.eligasht.service.model.flight.response.purchaseServiceFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePurchaseFlight {

    @SerializedName("PurchaseServiceResult")
    @Expose
    public PurchaseServiceResult purchaseServiceResult;

    public PurchaseServiceResult getPurchaseServiceResult() {
        return purchaseServiceResult;
    }

    public void setPurchaseServiceResult(PurchaseServiceResult purchaseServiceResult) {
        this.purchaseServiceResult = purchaseServiceResult;
    }

}
