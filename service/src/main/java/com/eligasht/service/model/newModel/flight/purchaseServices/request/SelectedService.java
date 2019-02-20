
package com.eligasht.service.model.newModel.flight.purchaseServices.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedService {

    @SerializedName("ServiceID")
    @Expose
    private String serviceID;
    @SerializedName("CommaSeperatedPassengerIDs")
    @Expose
    private Object commaSeperatedPassengerIDs;

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public Object getCommaSeperatedPassengerIDs() {
        return commaSeperatedPassengerIDs;
    }

    public void setCommaSeperatedPassengerIDs(Object commaSeperatedPassengerIDs) {
        this.commaSeperatedPassengerIDs = commaSeperatedPassengerIDs;
    }

}
