
package com.eligasht.service.model.insurance.response.PurchaseInsurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePurchaseInsurance {

    @SerializedName("PurchaseInsuranceResult")
    @Expose
    public PurchaseInsuranceResult purchaseInsuranceResult;

    public PurchaseInsuranceResult getPurchaseInsuranceResult() {
        return purchaseInsuranceResult;
    }

    public void setPurchaseInsuranceResult(PurchaseInsuranceResult purchaseInsuranceResult) {
        this.purchaseInsuranceResult = purchaseInsuranceResult;
    }

}
