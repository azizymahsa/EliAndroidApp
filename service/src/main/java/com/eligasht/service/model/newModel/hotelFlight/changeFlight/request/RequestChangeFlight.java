package com.eligasht.service.model.newModel.hotelFlight.changeFlight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestChangeFlight {

    @SerializedName("CurrentFlight")
    @Expose
    private String currentFlight;
    @SerializedName("FligthId")
    @Expose
    private String fligthId;

    public String getCurrentFlight() {
        return currentFlight;
    }

    public void setCurrentFlight(String currentFlight) {
        this.currentFlight = currentFlight;
    }

    public String getFligthId() {
        return fligthId;
    }

    public void setFligthId(String fligthId) {
        this.fligthId = fligthId;
    }

}