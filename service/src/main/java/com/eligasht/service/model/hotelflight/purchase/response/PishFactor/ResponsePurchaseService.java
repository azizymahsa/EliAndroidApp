
package com.eligasht.service.model.hotelflight.purchase.response.PishFactor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePurchaseService {

    @SerializedName("PurchaseServiceResult")
    @Expose
    private PurchaseServiceResult purchaseServiceResult;

    public PurchaseServiceResult getPurchaseServiceResult() {
        return purchaseServiceResult;
    }

    public void setPurchaseServiceResult(PurchaseServiceResult purchaseServiceResult) {
        this.purchaseServiceResult = purchaseServiceResult;
    }

}
