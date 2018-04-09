
package com.eligasht.service.model.insurance.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePurchaseInsurance {

    @SerializedName("PurchaseInsuranceResult")
    @Expose
    private PurchaseInsuranceResult purchaseInsuranceResult;

    public PurchaseInsuranceResult getPurchaseInsuranceResult() {
        return purchaseInsuranceResult;
    }

    public void setPurchaseInsuranceResult(PurchaseInsuranceResult purchaseInsuranceResult) {
        this.purchaseInsuranceResult = purchaseInsuranceResult;
    }

}
