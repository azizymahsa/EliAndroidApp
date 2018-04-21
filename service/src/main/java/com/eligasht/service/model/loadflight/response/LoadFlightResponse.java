
package com.eligasht.service.model.loadflight.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadFlightResponse {

    @SerializedName("LoadFlightResult")
    @Expose
    public LoadFlightResult loadFlightResult;

    public LoadFlightResult getLoadFlightResult() {
        return loadFlightResult;
    }

    public void setLoadFlightResult(LoadFlightResult loadFlightResult) {
        this.loadFlightResult = loadFlightResult;
    }

}
