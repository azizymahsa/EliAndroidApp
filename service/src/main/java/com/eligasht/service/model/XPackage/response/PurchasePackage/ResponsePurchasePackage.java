
package com.eligasht.service.model.XPackage.response.PurchasePackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePurchasePackage {

    @SerializedName("PurchasePackageResult")
    @Expose
    public PurchasePackageResult purchasePackageResult;

    public PurchasePackageResult getPurchasePackageResult() {
        return purchasePackageResult;
    }

    public void setPurchasePackageResult(PurchasePackageResult purchasePackageResult) {
        this.purchasePackageResult = purchasePackageResult;
    }

}
