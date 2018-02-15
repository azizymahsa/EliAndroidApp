
package com.eligasht.reservation.models.model.pack.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.eligasht.reservation.models.model.pack.PurchasePackageResult;

public class PurchaseRes {

    @SerializedName("PurchasePackageResult")
    @Expose
    private PurchasePackageResult purchasePackageResult;

    public PurchasePackageResult getPurchasePackageResult() {
        return purchasePackageResult;
    }

    public void setPurchasePackageResult(PurchasePackageResult purchasePackageResult) {
        this.purchasePackageResult = purchasePackageResult;
    }

}
