
package com.eligasht.service.model.newModel.hotelFlight.loadFlight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLoadFlight {

    @SerializedName("ToFlightId")
    @Expose
    private String toFlightId;
    @SerializedName("PreSearchUniqueId")
    @Expose
    private String preSearchUniqueId;

    public String getToFlightId() {
        return toFlightId;
    }

    public void setToFlightId(String toFlightId) {
        this.toFlightId = toFlightId;
    }

    public String getPreSearchUniqueId() {
        return preSearchUniqueId;
    }

    public void setPreSearchUniqueId(String preSearchUniqueId) {
        this.preSearchUniqueId = preSearchUniqueId;
    }

}
