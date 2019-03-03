
package com.eligasht.service.model.newModel.flight.purchaseServices.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestGetPurchaseServices {

    @SerializedName("AdditionalServicesObject")
    @Expose
    private AdditionalServicesObject additionalServicesObject;

    public AdditionalServicesObject getAdditionalServicesObject() {
        return additionalServicesObject;
    }

    public void setAdditionalServicesObject(AdditionalServicesObject additionalServicesObject) {
        this.additionalServicesObject = additionalServicesObject;
    }

}
