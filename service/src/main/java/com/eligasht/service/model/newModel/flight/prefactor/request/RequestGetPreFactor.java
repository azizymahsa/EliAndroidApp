
package com.eligasht.service.model.newModel.flight.prefactor.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestGetPreFactor {

    @SerializedName("PreFactorNo")
    @Expose
    private String preFactorNo;
    @SerializedName("PurchaseIdentity")
    @Expose
    private String purchaseIdentity;
    @SerializedName("PreFactorGUID")
    @Expose
    private String preFactorGUID;

    public String getPreFactorNo() {
        return preFactorNo;
    }

    public void setPreFactorNo(String preFactorNo) {
        this.preFactorNo = preFactorNo;
    }

    public String getPurchaseIdentity() {
        return purchaseIdentity;
    }

    public void setPurchaseIdentity(String purchaseIdentity) {
        this.purchaseIdentity = purchaseIdentity;
    }

    public String getPreFactorGUID() {
        return preFactorGUID;
    }

    public void setPreFactorGUID(String preFactorGUID) {
        this.preFactorGUID = preFactorGUID;
    }

}
