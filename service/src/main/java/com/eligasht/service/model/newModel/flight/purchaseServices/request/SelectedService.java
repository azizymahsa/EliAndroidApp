
package com.eligasht.service.model.newModel.flight.purchaseServices.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedService {

    @SerializedName("ServiceID")
    @Expose
    private String serviceID;

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

}
